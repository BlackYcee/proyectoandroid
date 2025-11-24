package com.inacap.colocolo.nav

import LoginScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.inacap.colocolo.screens.HomeScreen
import com.inacap.colocolo.screens.RegisterScreen
import com.inacap.colocolo.ui.AuthViewModel

fun NavGraphBuilder.appNavGraph(viewModel: AuthViewModel, navController: NavController) {
    composable("register") { backStackEntry ->
        RegisterScreen(viewModel, navController)
    }

    composable("login") { backStackEntry ->
        LoginScreen(viewModel, navController)
    }

    composable("home") { backStackEntry ->
        HomeScreen(viewModel, navController)
    }
}