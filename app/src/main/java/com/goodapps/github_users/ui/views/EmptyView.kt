package com.goodapps.github_users.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(modifier = Modifier.align(Alignment.Center), text = "EmptyView", fontSize = 24.sp)
    }
}