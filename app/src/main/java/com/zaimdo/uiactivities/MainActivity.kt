package com.kotlin_base_dev.uiactivities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kotlin_base_dev.ui.main.SectionsPagerAdapter
import com.zaimdo.R


class MainActivity : AppCompatActivity() {

    companion object {

        //net
        lateinit var net: String

        //cam
        lateinit var cam: String

        //adg
        lateinit var adg: String

        //cre
        lateinit var cre: String

        //subid1
        lateinit var subid1: String

        //subid2
        lateinit var subid2: String

        //subid3
        lateinit var subid3: String
    }



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

    //top alert
    var topAlert: ConstraintLayout? = null

    //top constraint
    var topConstraint: ConstraintLayout? = null

    //tab layout
    var tabLayout: TabLayout? = null

    //sectionpager
    lateinit var sectionsPagerAdapter: SectionsPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TASG", "test")

        //getting prefs
        getPrefs()

        //declaring vars
        setDeclaring()

        //section pager initialization to display tabs
        setupViewPager()

        //network callback for getting data
        setNetworkCallBacks()

        //check for network connection
        if (isNetworkAvailable()) {
        } else {
            setNonEthernetCase()
        }

        progressBar!!.isIndeterminate = false
        progressBar!!.visibility = View.GONE

        for (i in 0 until tabLayout!!.tabCount) {
            val tab = (tabLayout!!.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 10, 0)
            tab.requestLayout()
        }

        //on click info listener
        infoTabIcon?.setOnClickListener{
            startActivity(Intent(this@MainActivity, Info::class.java))
        }

    }

    // declaring main objects
    private fun setDeclaring() {
        //textview and image of non-inherent case
        textView = findViewById(R.id.text_non_Ithernet)

        infoTabIcon = findViewById(R.id.info_tab_icon)

        imageView = findViewById(R.id.non_Ithernet)
        //recyclerview
        recyclerView = findViewById(R.id.recyclerView)
        //progress bar
        progressBar = findViewById(R.id.progressBar)
        //info tab icon init
        // infoTabIcon = findViewById(R.id.info_tab_icon);
        //tabs layout
        tabLayout = findViewById(R.id.tabs_layout)
    }

    //setting image and text in non-ethernet case
    private fun setNonEthernetCase() {
        textView!!.visibility = View.VISIBLE
        imageView!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
        topAlert!!.visibility = View.GONE
        textViewPls!!.visibility = View.VISIBLE
//        infoTabIcon.setVisibility(View.GONE);
    }

    //setting ViewPager
    private fun setupViewPager() {
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs_layout)
        tabs.setupWithViewPager(viewPager)
    }

    //network availability check
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    //setting Network Callbacks
    private fun setNetworkCallBacks() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities == null) {

                // Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_LONG).show();
            }
            connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    /*
                    here you can add some features when ethernet comes back
                     */
                }

                override fun onLost(network: Network) {
                    /*
                    here you can add some features when ethernet connection lost
                     */
                }
            })
        }
    }

    //getprefs
    private fun getPrefs() {
        val settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE)
        net = settings.getString("network", "")!!
        cam = settings.getString("campaign", "")!!
        adg = settings.getString("adgroup", "")!!
        cre = settings.getString("creative", "")!!
        subid1 = settings.getString("sub1", "")!!
        subid2 = settings.getString("sub2", "")!!
        subid3 = settings.getString("sub3", "")!!
    }
}