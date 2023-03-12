package com.goodapps.github_users.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodapps.github_users.R
import com.goodapps.github_users.core.resources.ResourcesProvider
import com.goodapps.github_users.domain.interactors.UsersInteractor
import com.goodapps.github_users.domain.models.User
import com.goodapps.github_users.ui.models.Event
import com.goodapps.github_users.ui.models.common.toUIModel
import com.goodapps.github_users.ui.models.screen_models.UsersScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _screenModel = MutableStateFlow(UsersScreenModel())
    val screenModel: StateFlow<UsersScreenModel>
        get() = _screenModel

    private var isLoadingMore = false

    init {
        loadData()
    }

    private fun loadData(loadMore: Boolean = false) {
        if (!loadMore) {
            _screenModel.update { it.copy(showProgress = true) }
        } else {
            isLoadingMore = true
        }
        viewModelScope.launch {
            val fetchUsersResult = usersInteractor.fetchUsers(loadMore)
            if (fetchUsersResult.isSuccess) {
                val newUsers = fetchUsersResult.getOrDefault(emptyList()).map(User::toUIModel)
                if (loadMore) {
                    _screenModel.update { prevModel ->
                        prevModel.copy(
                            users = prevModel.users + newUsers,
                            showProgress = false,
                            errorEvent = null
                        )
                    }
                } else {
                    _screenModel.update { prevModel ->
                        prevModel.copy(
                            users = newUsers,
                            showProgress = false,
                            errorEvent = null
                        )
                    }
                }
                isLoadingMore = false
            } else {
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
    }

    fun loadMoreData(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (!isLoadingMore && lastVisibleItemPosition + visibleItemCount >= totalItemCount) {
            loadData(loadMore = true)
        }
    }
}