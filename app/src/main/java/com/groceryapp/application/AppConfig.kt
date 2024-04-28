package com.groceryapp.application

import android.app.Application
import com.groceryapp.ui.model.CartModel

class AppConfig : Application() {
    companion object {
        val cartList = ArrayList<CartModel>()
    }

    override fun onCreate() {
        super.onCreate()

    }
}