package com.example.showphoto.retrofitAPI.common

object Common {

    private var BASE_URL = "http://gallery.dev.webant.ru/api/"
    fun getUrl():String{
        return BASE_URL
    }
}