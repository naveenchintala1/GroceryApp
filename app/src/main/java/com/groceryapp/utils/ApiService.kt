package com.groceryapp.utils

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @get:POST("products")
    val data : Response<String>
}