package com.goodapps.github_users

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.goodapps.github_users.navigation.NavGraph
import com.goodapps.github_users.ui.theme.GitHubUsersTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        login {
            setContent {
                GitHubUsersTheme {
                    NavGraph()
                }
            }
//        }
    }

    private fun login(successAction: () -> Unit) {
        val provider = OAuthProvider.newBuilder("github.com")
        val firebaseAuth = FirebaseAuth.getInstance()
        val pendingResultTask = firebaseAuth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask
                .addOnSuccessListener {
                    (it.credential as OAuthCredential).accessToken?.let(viewModel::obtainGitHubUserToken)
                    successAction()
                }
                .addOnFailureListener {
                    Log.e("Auth", it.message, it)
                }
        } else {
            firebaseAuth
                .startActivityForSignInWithProvider(this, provider.build())
                .addOnSuccessListener {
                    (it.credential as OAuthCredential).accessToken?.let(viewModel::obtainGitHubUserToken)
                    successAction()
                }
                .addOnFailureListener {
                    Log.e("Auth", it.message, it)
                }
        }
    }
}