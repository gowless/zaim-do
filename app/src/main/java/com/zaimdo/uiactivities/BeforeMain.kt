package com.kotlin_base_dev.uiactivities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zaimdo.R


class BeforeMain : AppCompatActivity() {


    //info tab icon declaring
    var infoTabIcon: ImageView? = null

    //text pls
    var textViewPls: TextView? = null

    //img non-ithernet
    var imageView: ImageView? = null

    //text non-ithernet
    var textView: TextView? = null

    //progress
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_before_main)

        //check for network connection
        if (isNetworkAvailable()) {
        } else {
            setNonEthernetCase()
        }


        if (Splash.isEmpty != null) {
            if (Splash.isEmpty) {
                nonCategoriesStart()
            } else {
              //  progressBar!!.isIndeterminate = false
                mainStart()
            }
        } else {
            nonCategoriesStart()
        }
    }

    //starting mainactivity
    private fun mainStart() {
        startActivity(Intent(this@BeforeMain, MainActivity::class.java))
    }

    //checking network availability
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


    //setting image and text in non-ethernet case
    private fun setNonEthernetCase() {
        textView!!.visibility = View.VISIBLE
        imageView!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
        // infoTabIcon.setVisibility(View.GONE);
        textViewPls!!.visibility = View.VISIBLE
    }

    //starts NonCategory
    private fun nonCategoriesStart() {
        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this@BeforeMain, OneCategory::class.java))
            finish()
        }, 500)
    }

    //vars init
    private fun initItems(){

        progressBar = findViewById(R.id.progressBar3)

        //textview and image of non-inherent case

        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet)
        imageView = findViewById(R.id.non_Ithernet)
    }
}