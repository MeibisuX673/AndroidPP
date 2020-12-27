package com.example.showphoto


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.showphoto.adapter.RecyclerViewAdapter
import com.example.showphoto.presenters.NewPhotoPresenter
import com.example.showphoto.views.NewPhotoView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class NewPhoto : MvpAppCompatFragment(),NewPhotoView{


    @InjectPresenter
    lateinit var mnewPhotoPresenter: NewPhotoPresenter

    interface Cheking{
        fun checkInternet()
    }

    private var icheking:Cheking? = null
    private var recyclerView:RecyclerView? = null
    private var layoutManager:RecyclerView.LayoutManager? = null
    private var recyclerViewAdapter:RecyclerViewAdapter? = null

    var page:Int? = null


    var rootView : View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater?.inflate(R.layout.fragment_new_photo, container, false)

        recyclerView = rootView?.findViewById(R.id.recycler_view)

        page = 1
        mnewPhotoPresenter.start()
        return rootView
    }


    override fun startSending() {

        mnewPhotoPresenter.getPhotoList(page!!)
    }


    override fun showPhoto(newPhotoList: ArrayList<String>) {

        layoutManager = GridLayoutManager(rootView?.context, 2)
        recyclerView?.layoutManager = layoutManager
        recyclerViewAdapter = RecyclerViewAdapter(newPhotoList)
        recyclerView?.adapter = recyclerViewAdapter
        recyclerView?.setHasFixedSize(true)

   }


    override fun message(message: String) {
        icheking?.checkInternet()
        Toast.makeText(rootView?.context, message, Toast.LENGTH_SHORT).show()
    }


}