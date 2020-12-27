package com.example.showphoto.presenters

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.example.showphoto.model.ListPhoto
import com.example.showphoto.model.Photo
import com.example.showphoto.retrofitAPI.RetrofitClient
import com.example.showphoto.retrofitAPI.RetrofitServices
import com.example.showphoto.retrofitAPI.common.Common
import com.example.showphoto.views.NewPhotoView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
//import Alexandr Poliakov Valadimirovich
import org.reactivestreams.Subscriber
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Flow
import io.reactivex.rxjava3.core.Observer as Observer


@InjectViewState
class NewPhotoPresenter: MvpPresenter<NewPhotoView>() {


    val retrofitServices : RetrofitServices = RetrofitClient.getPhoto(Common.getUrl()).create(RetrofitServices::class.java)
    var isStart : Boolean = false
    var newPhotoListEncodedBase64Images : ArrayList<String>? = null


    fun getPhotoList(page:Int){
        requestPhoto(page)

    }

    fun start(){

        if(!isStart){
            Log.d("MAKSIM","true")
            newPhotoListEncodedBase64Images = ArrayList()
            viewState.startSending()
            isStart = true

        }else{
            return
        }

    }

   private  fun requestPhoto(page:Int){
        newPhotoListEncodedBase64Images?.clear()

        val dispose = retrofitServices.getListPhotoNew().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for(i in it.data){
                    val dispose2 = retrofitServices.getPhoto(i.image.id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            newPhotoListEncodedBase64Images?.add(it.file.substringAfter(','))
                            Log.d("MAKSIM","${it.name}")

                        },{
                            Log.d("MAKSIM","is dispose2")
                        },{

                            if(newPhotoListEncodedBase64Images?.size == 10){
                                Log.d("MAKSIM","DIspose yeas")
                                viewState.showPhoto(newPhotoListEncodedBase64Images!!)
                            }
                        })
                }


            },{
                Log.d("MAKSIM","is dispose")
            },{


            })


    }



}






