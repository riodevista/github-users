package com.goodapps.github_users.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.goodapps.github_users.ui.screens.UserDetailsScreen
import com.goodapps.github_users.ui.screens.UsersScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route
    ) {
        composable(NavScreen.Home.route) {
            UsersScreen(onNavigateToDetail = {
                navController.navigate(NavScreen.Detail.createRoute(it))
            })
        }

        composable(
            NavScreen.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = requireNotNull(backStackEntry.arguments?.getString("itemId"))
            UserDetailsScreen(itemId)
        }
    }
}