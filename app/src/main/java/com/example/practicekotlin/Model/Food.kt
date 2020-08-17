package com.example.practicekotlin.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    var title: String? = null,
    var url: String? = null,
    var price: String? = null,
    var id: Int? = null,
    var isLike: Boolean? = false
) : Parcelable

