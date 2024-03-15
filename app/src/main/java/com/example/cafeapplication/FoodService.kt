package com.example.cafeapplication

import retrofit2.Call
import retrofit2.http.GET

interface FoodService {
    @GET("/fooddata")
    fun getFoodData(): Call<List<Food>>
}
