package com.example.mvvmauthmodule.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mvvmauthmodule.presentation.screens.auth.LoginScreen
import com.example.mvvmauthmodule.presentation.screens.auth.RegisterScreen

fun NavGraphBuilder.authGraph(navController: NavController){
    navigation(startDestination = AuthScreenRoutes.Login.route, route = ScreenRoutes.Auth.route){
        composable(AuthScreenRoutes.Login.route){
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(ScreenRoutes.Home.route){
                        popUpTo(0)  // Pop all screens
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(AuthScreenRoutes.Register.route){
                        popUpTo(0)  // Pop all screens
                    }
                }
            )
        }
        composable(AuthScreenRoutes.Register.route){
            RegisterScreen(
                onRegisterSuccessNavigation = {
                    navController.navigate(ScreenRoutes.Home.route){
                        popUpTo(0)  // Pop all screens
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(AuthScreenRoutes.Login.route){
                        popUpTo(0)  // Pop all screens
                    }
                }
            )
        }
    }
}

sealed class AuthScreenRoutes(val route : String){
    object Login : AuthScreenRoutes("login")
    object Register : AuthScreenRoutes("register")
}