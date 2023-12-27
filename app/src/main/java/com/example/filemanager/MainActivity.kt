package com.example.filemanager

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var rootOfDirectory:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Build.VERSION.SDK_INT<30){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
                Log.v("TAG","permission denied")
            }else {
                Log.v("TAG","permission accepted")
            }
        }else {
            if(!Environment.isExternalStorageManager()){
                Log.v("TAG","permission denied")
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
            }else {
                Log.v("TAG","permission accepted")
            }
        }
        rootOfDirectory = Environment.getExternalStorageDirectory().path
        val firstFragment = BlankFragment.newInstance(rootOfDirectory)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,firstFragment).addToBackStack(null).commit()

    }




}

