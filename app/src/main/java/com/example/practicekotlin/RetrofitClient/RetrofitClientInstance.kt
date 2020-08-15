package com.example.practicekotlin.RetrofitClient


import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
//        //    Giúp in hoa chữ cái đầu của các biển Khởi tạo nhận dữ liệu của Model từ API cho giống với Json
//            val gson = GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//               .create()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }


}