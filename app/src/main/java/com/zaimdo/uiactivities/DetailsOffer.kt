package com.zaimdo.uiactivities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin_base_dev.uiactivities.InfoCloak
import com.kotlin_base_dev.uiactivities.Splash
import com.zaimdo.R

class DetailsOffer : AppCompatActivity() {

    //progressbar declaring
    var progressBar: ProgressBar? = null

    //scroll view declaring
    var scrollView: ScrollView? = null

    //declaring toolbar
    var toolbar: Toolbar? = null

    //string position number
    var position: Int? = null

    //image of offer
    var imageView: ImageView? = null

    //progressBarImage
    var progressBarImage: ProgressBar? = null

    //constraint
    var topAlert: ConstraintLayout? = null

    //text views
    lateinit var textAdress: TextView
    lateinit var textNumber: TextView
    lateinit var textMail: TextView
    lateinit var textSite: TextView
    lateinit var textPercent: TextView
    lateinit var textLicense: TextView
    lateinit var textTerms: TextView
    lateinit var textFistCredit: TextView
    lateinit var textNextCredit: TextView
    lateinit var textYUR: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_offer)

        //declaring items

        //declaring items
        declareItems()

        //getting intent

        //getting intent
        val intent = intent

        //getting extra number from cloak adapter

        //getting extra number from cloak adapter
        position = intent.getIntExtra("position", 0)

        //starting method

        //starting method
        parseDataToObjects()




        textTerms.setOnClickListener {
            val intent =
                Intent(this@DetailsOffer, InfoCloak::class.java)
            intent.putExtra("check", 1)
            startActivity(intent)
        }

    }

    //declaring items
    private fun declareItems() {
        //initializing views
        textTerms = findViewById(R.id.textTerms)
        //topAlert = findViewById(R.id.topConstraint)
        scrollView = findViewById(R.id.scrollView2)
        imageView = findViewById(R.id.imageOffer)
        textAdress = findViewById(R.id.textAdress)
        textNumber = findViewById(R.id.textNumber)
        textMail = findViewById(R.id.textMail)
        textSite = findViewById(R.id.textSite)
        textPercent = findViewById(R.id.textPercent)
        textLicense = findViewById(R.id.textLicense)
        textFistCredit = findViewById(R.id.textFirstCredit)
        textNextCredit = findViewById(R.id.textNextCredit)
        textYUR = findViewById(R.id.textYUR)

        //initializing progressBars
        progressBar = findViewById(R.id.progressBar4)
       // progressBarImage = findViewById(R.id.progressbarImage)

    }

    // parse data to fields
    @SuppressLint("SetTextI18n")
    private fun parseDataToObjects() {
        //parsing data to views
        scrollView!!.visibility = View.VISIBLE
        progressBar!!.isIndeterminate = false
        progressBar!!.visibility = View.GONE
        val URL: String = Splash.listDataAll[position!!].url


        textAdress.text = "• " + Splash.listDataAll.get(position!!).offer_name
        textNumber.text = "• " + Splash.listDataAll.get(position!!).detail.phone
        textMail.text = "• " + Splash.listDataAll.get(position!!).detail.email
        textSite.text = "• " + Splash.listDataAll.get(position!!).detail.site
        textPercent.text = "• " + Splash.listDataAll.get(position!!).detail.apr + "%"
        textLicense.text = "• " + Splash.listDataAll.get(position!!).detail.license
        textFistCredit.text = "• " + Splash.listDataAll.get(position!!).amount.from + "₴"
        textNextCredit.text = "• " + Splash.listDataAll.get(position!!).amount.to + "₴"
        textYUR.text = "• " + Splash.listDataAll.get(position!!).detail.address

        //parsing image to imageview
        Glide.with(this@DetailsOffer)
            .load(URL)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBarImage!!.isIndeterminate = false
                    progressBarImage!!.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBarImage!!.isIndeterminate = false
                    progressBarImage!!.visibility = View.GONE
                    return false
                }
            })
            .centerInside()
            .into(imageView!!)
    }

}