package com.example.practicekotlin.Model.newModel

open class BaseResponse<T> {
    var Title: String? = null
    var Result: T? = null
    var Status: MenuStatus? = null
}