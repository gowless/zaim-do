package com.kotlin_base_dev.uiactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zaimdo.R


class InfoOneCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }

    override fun onBackPressed() {
        startActivity(Intent(this@InfoOneCategory, OneCategory::class.java))
    }
}