package com.example.filemanager

import android.app.AlertDialog
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class Adapter(val listEntry:ArrayList<File>):RecyclerView.Adapter<Adapter.MyViewHolder>() {
    lateinit var rootOfDirectory:String
    lateinit var openNewFragment: (String)->Unit
    class MyViewHolder(var view: View):RecyclerView.ViewHolder(view){
        var textView = view.findViewById<TextView>(R.id.textView)

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
        val currentFile = listEntry?.get(position)
        val fullPath = currentFile?.absolutePath
        val name = fullPath?.substring(rootOfDirectory.length,fullPath.length)
        holder.textView.setText(name)
        holder.view.setOnClickListener{
            if(currentFile?.isDirectory!!){
                openNewFragment(fullPath!!)
            }
        }
        holder.view.setOnCreateContextMenuListener(object:OnCreateContextMenuListener{
            override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
            ) {
                menu?.add(0,0,0,"Delete")?.setOnMenuItemClickListener {
                    val builder:AlertDialog.Builder = AlertDialog.Builder(holder.view.context)
                    builder.setTitle("Xóa")
                        .setMessage("Bạn có muốn xóa?")
                        .setPositiveButton("OK"){dialog,id->
                            if(currentFile?.delete()!!){
                                listEntry.removeAt(position)
                                notifyDataSetChanged()
                                Toast.makeText(holder.view.context,"Deleted",Toast.LENGTH_LONG).show()

                            }else Toast.makeText(holder.view.context,"Fail to Delete",Toast.LENGTH_LONG).show()

                    }.show()
                    true
                }
                menu?.add(0,1,1,"Rename")?.setOnMenuItemClickListener {
                    val builder:AlertDialog.Builder = AlertDialog.Builder(holder.view.context)
                    builder.setTitle("Rename")
                        .setMessage("Bạn có muốn đổi tên?")
                        .setPositiveButton("OK"){dialog,id->
                            if(currentFile?.delete()!!){
                                listEntry.removeAt(position)
                                notifyDataSetChanged()
                                Toast.makeText(holder.view.context,"Deleted",Toast.LENGTH_LONG).show()

                            }else Toast.makeText(holder.view.context,"Fail to Delete",Toast.LENGTH_LONG).show()

                        }.show()
                    true
                }
            }
        })
        Log.v("TAG","onbindviewholder")
        Log.v("TAG",rootOfDirectory)
    }
}