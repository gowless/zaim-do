package com.kotlin_base_dev

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.onesignal.OneSignal

class MainClass : Application() {

    //immutable values
    val appToken = "gxu9g3zadpfk"

    companion object{
        //declare main vars of information
        lateinit var trackerToken: String
        lateinit var trackerName: String
        lateinit var network: String
        lateinit var campaign: String
        lateinit var adgroup: String
        lateinit var creative: String
        lateinit var adid: String
        lateinit var adgroup_id: String
        lateinit var creative_id: String
        lateinit var campaign_id: String
        var font: Float = 0F
    }


    //declare sharedprefs
    lateinit var settings: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    val APPID = "com.tvoykreditonlain"
    val REGION = "ua"
    private val ONESIGNAL_APP_ID = "3d5db181-4657-4985-a2c1-178163c4db99"

    @SuppressLint("CommitPrefEdits")
    override fun onCreate() {
        super.onCreate()
        //init sharedprefs
        settings = getSharedPreferences("LOCAL", Context.MODE_PRIVATE)
        editor = settings.edit()

        //getting font scale
        font = resources.configuration.fontScale


        // Configure adjust SDK.
        val environment = AdjustConfig.ENVIRONMENT_SANDBOX
        val config = AdjustConfig(this, appToken, environment)

        // enable all logs
        config.setLogLevel(LogLevel.VERBOSE)

        //attribution listener to get data
        config.setOnAttributionChangedListener { attribution ->
            trackerToken = attribution.trackerToken
            trackerName = attribution.trackerName
            network = attribution.network
            campaign = attribution.campaign
            adgroup = attribution.adgroup
            creative = attribution.creative
            adid = attribution.adid


            //put to sharedprefs
            editor.putString("trackerToken", trackerToken)
            editor.putString("trackerName", trackerName)
            editor.putString("network", network)
            editor.putString("campaign", campaign)
            editor.putString("adgroup", adgroup)
            editor.putString("creative", creative)
            editor.putString("adid", adid)
            editor.commit()
        }
        Adjust.onCreate(config)

        // Enable verbose OneSignal logging to debug issues if needed.

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        //calling method to get fb attribution

        //calling method to get fb attribution
        getFBAtts()
    }

    //fb attribution subid method
    private fun getFBAtts() {
        //checking on first launch
        if (settings.contains("firstlaunch")) {

        } else {
            //switch case
            when (settings.getString("network", "")) {
                "Facebook Installs" -> {

                }
                "Google Ads UAC" -> {
                    editor.putString("sub1", "uac")
                    editor.putString("sub2", "uac")
                    editor.putString("sub3", "uac")
                    editor.commit()
                }
                "Organic" -> {
                    editor.putString("sub1", "organic")
                    editor.putString("sub2", "organic")
                    editor.putString("sub3", "organic")
                    editor.commit()
                }
                "Unattributed" -> {
                    editor.putString("sub1", "unattributed")
                    editor.putString("sub2", "unattributed")
                    editor.putString("sub3", "unattributed")
                    editor.commit()
                }
            }
        }

        //adding tag of first launch
        if (settings.contains("firstlaunch")) {

        } else {
            editor.putInt("firstlaunch", 1)
            editor.commit()
        }
    }


}