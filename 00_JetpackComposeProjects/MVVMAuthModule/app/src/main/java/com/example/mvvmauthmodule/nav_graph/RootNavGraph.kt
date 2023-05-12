package com.example.mvvmauthmodule.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmauthmodule.presentation.screens.HomeScreen

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),

) {
    NavHost(
        modifier=modifier,
        navController = navController,
        startDestination = ScreenRoutes.Auth.route
    ){
        authGraph(navController)
        composable(ScreenRoutes.Home.route){
            HomeScreen()
        }
    }
}

sealed class ScreenRoutes(val route : String){
    object Auth : ScreenRoutes("auth")
    object Home : ScreenRoutes("home")
}

