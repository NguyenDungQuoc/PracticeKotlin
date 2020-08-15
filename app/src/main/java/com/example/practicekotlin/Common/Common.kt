package com.example.practicekotlin.Common

import com.example.practicekotlin.RetrofitClient.RetrofitClientInstance
import retrofit2.Retrofit

object Common {
    private const val  BASE_URL = "https://81fdd931-104f-47b2-ab02-315789309b9f.mock.pstmn.io"
            val retrofitService : Retrofit
    get() = RetrofitClientInstance.getClient(BASE_URL)
}