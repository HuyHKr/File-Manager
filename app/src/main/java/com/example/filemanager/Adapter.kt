package com.example.filemanager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class Adapter(val listEntry:Array<File>?):RecyclerView.Adapter<Adapter.MyViewHolder>() {
    lateinit var rootOfDirectory:String
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.v("TAG","oncreateviewholder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        Log.v("TAG","oncreateviewholder2")
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEntry?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fullPath = listEntry?.get(position)?.absolutePath
        val name = fullPath?.substring(rootOfDirectory.length,fullPath.length)
        holder.textView.setText(name)
        Log.v("TAG","onbindviewholder")
        Log.v("TAG",rootOfDirectory)
    }
}