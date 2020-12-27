package com.example.showphoto.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainActivityView : MvpView{
    fun checkInternet()
    fun stop()
    fun start()
}