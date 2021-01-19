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
import com.kotlin_base_dev.adapters.OneCategoryAdapter
import com.zaimdo.R


class OneCategory : AppCompatActivity() {

    //recyclerview
    var recyclerView: RecyclerView? = null

    //progress bar
    var progressBar: ProgressBar? = null

    //info tab icon declaring
    var infoTabIcon: ImageView? = null

    //img non-ithernet
    var imageView: ImageView? = null

    //text non-ithernet
    var textView: TextView? = null

    //text pls
    var textViewPls: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_categories)


        //declaring items
        declaringItems()

        //check for network connection
        if (isNetworkAvailable()) {
            settingAdapter()
        } else {
            //setting non-ethernet page
            setNonEthernetCase()
        }

    }

    //setting image and text in non-ethernet case
    private fun setNonEthernetCase() {
        textView!!.visibility = View.VISIBLE
        imageView!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
        // infoTabIcon.setVisibility(View.GONE);
        textViewPls!!.visibility = View.VISIBLE
    }

    //initializing items
    private fun declaringItems() {
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet)
        imageView = findViewById(R.id.non_Ithernet)

        //recyclerview
        recyclerView = findViewById(R.id.recyclerView)

        //progress bar
        progressBar = findViewById(R.id.progressBar)

        //info tab icon init
        // infoTabIcon = findViewById(R.id.info_tab_icon);
    }

    //checking network availability
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    //setting adapter
    private fun settingAdapter() {
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.isDrawingCacheEnabled = true
        recyclerView!!.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        val recyclerAdapterAllMain =
           OneCategoryAdapter(Splash.listDataAll)
        recyclerView!!.adapter = recyclerAdapterAllMain
        progressBar!!.isIndeterminate = false
        progressBar!!.visibility = View.GONE
    }
}