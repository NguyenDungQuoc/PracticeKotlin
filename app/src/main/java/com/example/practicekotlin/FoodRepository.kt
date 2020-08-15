package com.example.practicekotlin

import androidx.lifecycle.MutableLiveData
import com.example.practicekotlin.Common.Common
import com.example.practicekotlin.Interface.RetrofitService
import com.example.practicekotlin.Model.FoodModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepository {
    private val retrofitService: RetrofitService = Common.retrofitService.create(RetrofitService::class.java)
    //
    fun getAllProducts(callback: (FoodModel?)->(Unit),callbackError: (String?) ->(Unit)) {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<FoodModel>()
        //getAllProducts from Service
        retrofitService.getAllProducts().enqueue(object : Callback<FoodModel> {
            override fun onFailure(call: Call<FoodModel>, t: Throwable) {
                callbackError.invoke("Lay du lieu ko thanh cong")
            }

            override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
                callback.invoke(response.body())
            }
        })
    }
}