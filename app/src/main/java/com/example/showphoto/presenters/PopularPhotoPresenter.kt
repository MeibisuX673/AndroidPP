package com.example.showphoto.presenters

import android.util.Log
import com.example.showphoto.model.Photo
import com.example.showphoto.retrofitAPI.RetrofitClient
import com.example.showphoto.retrofitAPI.RetrofitServices
import com.example.showphoto.retrofitAPI.common.Common
import com.example.showphoto.views.NewPhotoView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class PopularPhotoPresenter : MvpPresenter<NewPhotoView>() {

    val retrofitServices : RetrofitServices = RetrofitClient.getPhoto(Common.getUrl()).create(RetrofitServices::class.java)
    var isStart : Boolean = false
    var popularPhotoListEncodedBase64Images : ArrayList<String>? = null

    fun getPhotoList(page:Int){
        requestPhoto(page)

    }

    fun start(){

        if(!isStart){
            Log.d("MAKSIM","true")
            popularPhotoListEncodedBase64Images = ArrayList()
            viewState.startSending()
            isStart = true

        }else{
            return
        }

    }

    private fun requestPhoto(page: Int){

        val dispose = retrofitServices.getListPopularPhoto().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    for(i in it.data){
                        val dispose2 = retrofitServices.getPhoto(i.image.id)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    popularPhotoListEncodedBase64Images?.add(it.file.substringAfter(','))
                                    Log.d("MAKSIM","${it.name}")

                                },{
                                    Log.d("MAKSIM","is dispose2")
                                },{

                                    if(popularPhotoListEncodedBase64Images?.size == 10){
                                        Log.d("MAKSIM","DIspose yeas")
                                        viewState.showPhoto(popularPhotoListEncodedBase64Images!!)
                                    }
                                })
                    }


                },{
                    Log.d("MAKSIM","is dispose")
                },{


                })


    }
}