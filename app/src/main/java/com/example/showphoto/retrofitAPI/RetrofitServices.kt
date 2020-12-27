package com.example.showphoto.retrofitAPI

import com.example.showphoto.model.ListPhoto
import com.example.showphoto.model.Photo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface RetrofitServices {
    @GET("media_objects/{id}")
    fun getPhoto(@Path("id") id:Int): Observable<Photo>
    @GET(value = "http://gallery.dev.webant.ru/api/photos?new=true&popular=false&page=1&limit=10")
    fun getListPhotoNew() : Observable<ListPhoto>
    @GET(value = "photos?new=false&popular=true&limit=10")
    fun getListPopularPhoto() : Observable<ListPhoto>
}