package com.example.practicekotlin.Interface

import com.example.practicekotlin.Model.FoodModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    /**
     * @GET declares an HTTP GET request
     */
    @GET("/getAllProducts")
    fun getAllProducts(): Call<FoodModel>
}
