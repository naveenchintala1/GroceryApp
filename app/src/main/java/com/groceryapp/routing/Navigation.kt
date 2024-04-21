package com.groceryapp.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groceryapp.ui.detail.DetailScreen
import com.groceryapp.ui.login.LoginScreen
import com.groceryapp.ui.main.MainScreen
import com.groceryapp.ui.sign_up.SignUpScreen
import com.groceryapp.ui.splash.SplashScreen
import com.groceryapp.ui.wishlist.WishlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route) {
            DetailScreen(navController = navController)
        }
        composable(route = Screen.WishlistScreen.route) {
            WishlistScreen(navController = navController)
        }
    }

}