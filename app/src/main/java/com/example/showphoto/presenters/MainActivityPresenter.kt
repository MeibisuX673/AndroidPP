package com.example.showphoto.presenters

import android.content.Context
import android.net.ConnectivityManager
import com.example.showphoto.views.MainActivityView
//import Alexandr Poliakov Valadimirovich
import moxy.MvpPresenter

class MainActivityPresenter: MvpPresenter<MainActivityView>() {

    private var check:Boolean = false

    fun noInternet(){
        viewState.stop()
    }

    fun internetOn(){
        viewState.start()
    }
    fun startActivity(){
        viewState.checkInternet()
    }


}