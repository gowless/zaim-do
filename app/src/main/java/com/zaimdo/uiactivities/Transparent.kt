package com.kotlin_base_dev.uiactivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zaimdo.R

class Transparent : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)
        //starting splash activity
        startActivity(Intent(this@Transparent, Splash::class.java))
    }
}