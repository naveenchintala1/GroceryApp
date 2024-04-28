package com.groceryapp.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groceryapp.ui.cartScreen.CartScreen
import com.groceryapp.ui.detail.DetailScreen
import com.groceryapp.ui.login.LoginScreen
import com.groceryapp.ui.main.MainScreen
import com.groceryapp.ui.reviewScreen.ReviewScreen
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
        composable(route = Screen.DetailScreen.route+ "/{name}"+"/{image}"+"/{price}"+"/{detail}") {
            val name = it.arguments?.getString("name")
            val image = it.arguments?.getString("image").toString().toInt()
            val price = it.arguments?.getString("price")
            val detail = it.arguments?.getString("detail")
            if (name != null) {
                if (image != null) {
                    if (price != null) {
                        if (detail != null) {
                            DetailScreen(
                                navController = navController,
                                name = name,
                                image = image,
                                price = price,
                                detail = detail
                            )
                        }
                    }
                }
            }
        }
        composable(route = Screen.WishlistScreen.route) {
            WishlistScreen(navController = navController)
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen(navController = navController)
        }
        composable(route = Screen.ReviewScreen.route) {
            ReviewScreen(navController = navController)
        }
    }

}