package com.example.showphoto.views

import android.widget.Button
import com.example.showphoto.model.ListPhoto
import com.example.showphoto.model.Photo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = SingleStateStrategy::class)
interface NewPhotoView: MvpView {

    fun startSending()

    fun showPhoto(newPhotoList: ArrayList<String>)
    fun message(message:String)



}