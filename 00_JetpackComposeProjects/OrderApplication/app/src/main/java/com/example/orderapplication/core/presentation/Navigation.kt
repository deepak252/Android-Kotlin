package com.example.orderapplication.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.orderapplication.order_feature.presentation.screens.OrderChooseProductsScreen
import com.example.orderapplication.order_feature.presentation.screens.OrderChooseVendorScreen
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
        composable(ScreenRoutes.OrderChooseVendorScreen.route){
            OrderChooseVendorScreen(navController = navController)
        }
        composable(ScreenRoutes.OrderChooseProductsScreen.route+"/{vendorId}"){
            OrderChooseProductsScreen(
                navController = navController,
                vendorId = it.arguments?.getString("vendorId")
            )
        }

    }

}

sealed class ScreenRoutes(val route : String){
    object OrderScreen : ScreenRoutes("orderScreen")
    object OrderChooseVendorScreen : ScreenRoutes("orderChooseVendorScreen")
    object OrderChooseProductsScreen : ScreenRoutes("orderChooseProductsScreen")
}