package com.kotlin_base_dev.uiactivities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin_base_dev.adapters.CloakAdapter
import com.zaimdo.R


class Cloak : AppCompatActivity() {

    var progressBar: ProgressBar? = null

    var recyclerView: RecyclerView? = null

    //img non-ithernet
    var imageView: ImageView? = null

    //text non-ithernet
    var textView: TextView? = null

    //info tab icon declaring
    var infoTabIcon: ImageView? = null

    //top text button
    var topTextCloak: TextView? = null

    //text pls
    var textViewPls: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cloak)

        //declaring vars
        declareVars()

        //check for network connection
        if (isNetworkAvailable()) {
        } else {
            setNonEthernetCase()
        }

        // setting adapter
        settingAdapter()

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
        //infoTabIcon.setVisibility(View.GONE);
        textViewPls!!.visibility = View.VISIBLE
    }

    // setting cloak adapter
    private fun settingAdapter() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = CloakAdapter(Splash.listDataAll)
        progressBar!!.isIndeterminate = false
        progressBar!!.visibility = View.GONE
    }

    // declaring main vars
    private fun declareVars() {
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet)
        imageView = findViewById(R.id.non_Ithernet)

        //info tab icon init
        // infoTabIcon = findViewById(R.id.info_tab_icon);
        progressBar = findViewById(R.id.progressBar2)

    }

}