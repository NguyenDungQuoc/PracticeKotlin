package com.example.practicekotlin.Common

import com.example.practicekotlin.RetrofitClient.RetrofitClientInstance
import retrofit2.Retrofit

object Common {
    private const val  BASE_URL = "https://d7eeb91b-1760-47e6-a68c-e0d4d2cab8bf.mock.pstmn.io"
            val retrofitService : Retrofit
    get() = RetrofitClientInstance.getClient(BASE_URL)
}