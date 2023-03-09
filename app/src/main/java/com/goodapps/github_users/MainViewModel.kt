package com.goodapps.github_users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodapps.github_users.domain.interactors.UsersInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usersInteractor: UsersInteractor) :
    ViewModel() {

    fun obtainGitHubUserToken(token: String) {
        viewModelScope.launch {
            usersInteractor.saveUserToken(token)
        }
    }
}