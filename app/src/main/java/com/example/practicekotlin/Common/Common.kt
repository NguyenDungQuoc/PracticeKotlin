package com.example.practicekotlin.Common

import com.example.practicekotlin.RetrofitClient.RetrofitClientInstance
import retrofit2.Retrofit

object Common {
    private const val  BASE_URL = "https://35a2aa07-3da0-4b4f-b581-5542e02e7b09.mock.pstmn.io"
            val retrofitService : Retrofit
    get() = RetrofitClientInstance.getClient(BASE_URL)
}