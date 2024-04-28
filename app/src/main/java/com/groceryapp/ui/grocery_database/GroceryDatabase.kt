package com.groceryapp.ui.grocery_database

import android.content.Context
import android.content.SharedPreferences

class GroceryDatabase(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("grocery_database", Context.MODE_PRIVATE)

    fun saveData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue) ?: defaultValue
    }
}