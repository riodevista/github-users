package com.goodapps.github_users.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.goodapps.github_users.R
import com.goodapps.github_users.ui.models.common.UserUIModel
import com.goodapps.github_users.ui.viewmodels.UsersViewModel
import com.goodapps.github_users.ui.views.EmptyView
import com.goodapps.github_users.ui.views.LoadingIndicator

@Composable
fun UsersScreen(onNavigateToDetail: (String) -> Unit) {
    val context = LocalContext.current
    val viewModel: UsersViewModel = hiltViewModel()
    val screenModel = viewModel.screenModel.collectAsState()

    if (screenModel.value.showProgress) {
        LoadingIndicator()
    } else {
        val users = screenModel.value.users
        if (users.isNotEmpty()) {
            UsersList(users = screenModel.value.users) {
                onNavigateToDetail(it.login)
            }
        } else {
            EmptyView()
        }
    }

    LaunchedEffect(key1 = screenModel.value.errorEvent) {
        screenModel.value.errorEvent?.getContentIfNotHandled()?.let(context::showToast)
    }
}

@Composable
fun UsersList(users: List<UserUIModel>, onItemClick: (UserUIModel) -> Unit) {
    LazyColumn {
        itemsIndexed(users) { index, item ->
            UserItem(user = item, onItemClick = onItemClick)
            if (index < users.size - 1) {
                Spacer(modifier = Modifier.height(7.5.dp))
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .padding(horizontal = 16.dp)
                        .alpha(0.5f),
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(3.5.dp))
            } else {
                Spacer(modifier = Modifier.height(3.5.dp))
            }
        }
    }
}

@Composable
fun UserItem(user: UserUIModel, onItemClick: (UserUIModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemClick.invoke(user)
            }
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
    }
}


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
