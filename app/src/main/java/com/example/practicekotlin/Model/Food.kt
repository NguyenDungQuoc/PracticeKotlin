package com.example.practicekotlin.Model

class Food(status: StatusFood, result: MutableList<Food>) : FoodModel(status, result) {
    var title: String? = null
    var url: String? = null
    var price: String? = null
}

