package com.goodapps.github_users.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.goodapps.github_users.R
import com.goodapps.github_users.ui.models.common.DetailedUserUIModel
import com.goodapps.github_users.ui.viewmodels.UsersDetailsViewModel
import com.goodapps.github_users.ui.views.EmptyView
import com.goodapps.github_users.ui.views.LoadingIndicator

@Composable
fun UserDetailsScreen(userLogin: String) {
    val viewModel: UsersDetailsViewModel = hiltViewModel()
    val screenModel = viewModel.screenModel.collectAsState()
    val context = LocalContext.current

    val screenPrepared = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!screenPrepared.value) {
            viewModel.prepareScreenModel(userLogin)
            screenPrepared.value = true
        }
    }

    if (screenModel.value.showProgress) {
        LoadingIndicator()
    } else {
        val user = screenModel.value.user
        if (user != null) {
            DetailedUserItem(user)
        } else {
            EmptyView()
        }
    }

    LaunchedEffect(key1 = screenModel.value.errorEvent) {
        screenModel.value.errorEvent?.getContentIfNotHandled()?.let(context::showToast)
    }
}

@Composable
fun DetailedUserItem(user: DetailedUserUIModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape),
                model = user.avatarUrl ?: R.drawable.ic_mock_avatar,
                contentDescription = "avatar",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = user.login, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = user.id.toString(), fontWeight = FontWeight.Normal, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Name: ${user.name ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: ${user.email ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Followers: ${user.followers ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Following: ${user.following ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Creation date: ${user.createdAt ?: "-"}")
        Spacer(modifier = Modifier.height(8.dp))
    }
}

