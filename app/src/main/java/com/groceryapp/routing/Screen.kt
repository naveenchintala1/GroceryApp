package com.groceryapp.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object WishlistScreen: Screen("wishlist_screen")
    object DetailScreen: Screen("detail_screen")
    object CartScreen: Screen("cart_screen")
    object ReviewScreen: Screen("review_screen")

}