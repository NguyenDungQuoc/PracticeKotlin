package com.example.practicekotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicekotlin.Model.Food
import com.example.practicekotlin.Model.FoodModel
import com.example.practicekotlin.Model.StatusFood

class FoodViewModel() : ViewModel() {
    var status: StatusFood? = null
    var foodRepository: FoodRepository = FoodRepository()
    var result: MutableLiveData<FoodModel> = MutableLiveData<FoodModel>( )
    var errorMessage : MutableLiveData<String>? = MutableLiveData()

    fun getProduct() {
        foodRepository.getAllProducts( {

            result.value = it
        }, {
            errorMessage?.value = it
        })
    }
}





