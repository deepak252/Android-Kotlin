package com.example.orderapplication.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orderapplication.order_feature.presentation.screens.OrderScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.OrderScreen.route
    ){
        composable(ScreenRoutes.OrderScreen.route){
            OrderScreen(navController = navController)
        }

    }

}

sealed class ScreenRoutes(val route : String){
    object OrderScreen : ScreenRoutes("orderScreen")
    object OrderChooseDelivererScreen : ScreenRoutes("orderChooseDelivererScreen")
    object OrderChooseProductsScreen : ScreenRoutes("orderChooseProductsScreen")
}