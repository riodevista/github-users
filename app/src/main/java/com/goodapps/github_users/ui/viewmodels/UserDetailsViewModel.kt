package com.goodapps.github_users.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodapps.github_users.R
import com.goodapps.github_users.core.resources.ResourcesProvider
import com.goodapps.github_users.domain.interactors.UsersInteractor
import com.goodapps.github_users.ui.models.Event
import com.goodapps.github_users.ui.models.common.toUIModelDetailed
import com.goodapps.github_users.ui.models.screen_models.UsersDetailsScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersDetailsViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _screenModel = MutableStateFlow(UsersDetailsScreenModel())
    val screenModel: StateFlow<UsersDetailsScreenModel>
        get() = _screenModel

    fun prepareScreenModel(userLogin: String) {
        viewModelScope.launch {
            loadData(userLogin)
        }
    }

    private fun loadData(login: String) {
        _screenModel.update { it.copy(showProgress = true) }
        viewModelScope.launch {
            val fetchUserResult = usersInteractor.fetchDetailedUser(login)
            if (fetchUserResult.isSuccess) {
                fetchUserResult.getOrNull().let { user ->
                    if (user != null) {
                        _screenModel.update {
                            it.copy(
                                user = user.toUIModelDetailed(),
                                showProgress = false,
                                errorEvent = null
                            )
                        }
                    } else {
                        stopProgressAndShowError()
                    }
                }
            } else {
                stopProgressAndShowError()
            }
        }
    }

    private fun stopProgressAndShowError() {
        _screenModel.update {
            it.copy(
                showProgress = false,
                errorEvent = Event(
                    resourcesProvider.getString(
                        R.string.default_error_message
                    )
                )
            )
        }
    }
}

