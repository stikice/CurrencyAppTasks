package com.example.currencyapp1.model

import androidx.annotation.DrawableRes

sealed class RecyclerDataClass{
    class Currency(val name: String, val country: String, val count: Int, @DrawableRes val imageRes: Int): RecyclerDataClass()
    class ActionButton(val actionName: String, @DrawableRes val imageRes: Int): RecyclerDataClass()
}
