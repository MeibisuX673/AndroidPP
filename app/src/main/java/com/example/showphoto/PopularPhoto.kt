package com.example.showphoto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.showphoto.adapter.RecyclerViewAdapter
import com.example.showphoto.presenters.PopularPhotoPresenter
import com.example.showphoto.views.NewPhotoView

import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class PopularPhoto : MvpAppCompatFragment(),NewPhotoView{

    @InjectPresenter
    lateinit var mPopularPhotoPresenter: PopularPhotoPresenter

    private var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var recyclerViewAdapter:RecyclerViewAdapter? = null
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
        mPopularPhotoPresenter.start()
        return rootView
    }

    override fun startSending() {
        mPopularPhotoPresenter.getPhotoList(page!!)
    }



    override fun showPhoto(newPhotoList: ArrayList<String>) {

        layoutManager = GridLayoutManager(rootView?.context,2)
        recyclerView?.layoutManager = layoutManager
        recyclerViewAdapter = RecyclerViewAdapter(newPhotoList)
        recyclerView?.adapter = recyclerViewAdapter
        recyclerView?.setHasFixedSize(true)



    }

    override fun message(message: String) {
        Toast.makeText(rootView?.context,message, Toast.LENGTH_SHORT).show()
    }




}