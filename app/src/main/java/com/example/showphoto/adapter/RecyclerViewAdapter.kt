package com.example.showphoto.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
//import Alexandr Poliakov Valadimirovich
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.showphoto.R


class RecyclerViewAdapter(listNewPhoto: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var photoListNew:ArrayList<String> = listNewPhoto


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.single_view,parent,false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var decodeBase64 = Base64.decode(photoListNew[position], Base64.DEFAULT)
        Log.d("image4", photoListNew[position].toString())
        var bitmap = BitmapFactory.decodeByteArray(decodeBase64, 0, decodeBase64.size)
        var bmHalf = Bitmap.createScaledBitmap(bitmap,bitmap.width/2,bitmap.height/2,false)


        holder.imageView?.setImageBitmap(bmHalf)

    }

    override fun getItemCount(): Int {
        return photoListNew.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.imageView)

    }

}