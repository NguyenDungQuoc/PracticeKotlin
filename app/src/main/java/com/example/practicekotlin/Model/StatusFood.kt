package com.example.practicekotlin.Model

import java.security.MessageDigest

class StatusFood(status: StatusFood, result: MutableList<Food>) : FoodModel(status, result) {
    var code: Int? = null
    var message: String? = null}


