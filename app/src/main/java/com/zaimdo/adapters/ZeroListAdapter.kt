package com.kotlin_base_dev.adapters

import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kotlin_base_dev.MainClass
import com.kotlin_base_dev.uiactivities.MainActivity
import com.zaimdo.network.models.getmodels.Listoffers
import com.zaimdo.R
import java.util.regex.Pattern


class ZeroListAdapter(private val data: List<Listoffers>) :
    RecyclerView.Adapter<ZeroListAdapter.MyViewHolder>() {


    //declaring main-attributes fields
    lateinit private var campaing:String
    lateinit private var campaign_id:String
    lateinit private var creative_id:String
    lateinit private var creative:String
    lateinit private var adgroup:String
    lateinit private var adgroup_id:String
    lateinit private var string:String



    override fun getItemCount() = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        if (MainClass.font > 1 && MainClass.font <= 1.25) {
            if (viewType == 1) {
                view = inflater.inflate(R.layout.fragment_large_top, parent, false)
            } else {
                view = inflater.inflate(R.layout.fragment_large, parent, false)
            }
        } else if (MainClass.font >= 1.3) {
            if (viewType == 1) {
                view = inflater.inflate(R.layout.fragment_exlarge_top, parent, false)
            } else {
                view = inflater.inflate(R.layout.fragment_exlarge, parent, false)
            }
        } else {
            if (viewType == 1) {
                view = inflater.inflate(R.layout.fragment_top, parent, false)
            } else {
                view = inflater.inflate(R.layout.fragment, parent, false)
            }
        }
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //setting holders to textViews
        holder.percentRate.text = data[position].amount.from.toString()
        holder.firstCreditSum.text = data[position].amount.to.toString()

        //setting image holder with glide
        Glide.with(Activity())
            .load(data[position].img)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    holder.progressBarGlide.isIndeterminate = false
                    holder.progressBarGlide.visibility = View.GONE
                    return false
                }
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    holder.progressBarGlide.isIndeterminate = false
                    holder.progressBarGlide.visibility = View.GONE
                    //do something when picture already loaded
                    return false
                }
            })
            .into(holder.imgCompany)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var percentRate: TextView
        var firstCreditSum: TextView
        var imgCompany: ImageView
        var progressBarGlide: ProgressBar

        init {
            percentRate = itemView.findViewById(R.id.percentRate)
            firstCreditSum = itemView.findViewById(R.id.firstCreditSum)
            imgCompany = itemView.findViewById(R.id.imgCompany)
            progressBarGlide = itemView.findViewById(R.id.progressBarGlide)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    //parsing link to update for button
    fun parseLinkFromApi(position: Int): String? {
        // https://tds.pdl-profit.com?affid=18827&offer_id=1158&subid={client_id}&subid2={advertising_id}&subid3={app}&utm_source={source}&utm_campaign={campaign}&utm_adgroup={adgroup}&utm_adposition={adset}&utm_creative={chanel}"
        val liste: Listoffers = data.get(position)
        //Main URI declaring and initialising
        var mainEditedURI: String = liste.url
        //manipulating with main string, changing parameters
        mainEditedURI = mainEditedURI.replace(
            Pattern.quote("{client_id}").toRegex(),
            MainActivity.subid1
        )
        mainEditedURI = mainEditedURI.replace(
            Pattern.quote("{advertising_id}").toRegex(),
            MainActivity.subid2
        )
        mainEditedURI = mainEditedURI.replace(
            Pattern.quote("{app}").toRegex(),
            MainActivity.subid3
        )

        //if organic/non-organic campaign
        if (MainActivity.net.equals("Organic")) {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{source}").toRegex(),
                "organic"
            )
        } else if (MainActivity.net.equals("Unattributed")) {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{source}").toRegex(),
                "unattributed"
            )
        } else {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{source}").toRegex(),
                MainActivity.net
            )
        }


        //if organic/non-organic campaign
        if (MainActivity.cam.equals("")) {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{campaign}").toRegex(),
                "organic"
            )
        } else {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{campaign}").toRegex(),
                MainActivity.cam
            )
        }


        //if organic/non-organic adgroup
        if (MainActivity.adg.equals("")) {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{adgroup}").toRegex(),
                "organic"
            )
        } else {
            mainEditedURI = mainEditedURI.replace(
                Pattern.quote("{adgroup}").toRegex(),
                MainActivity.adg
            )
        }

        //if organic/non-organic adset
        mainEditedURI = mainEditedURI.replace(Pattern.quote("{adset}").toRegex(), "organic")

        //if organic/non-organic chanel
        mainEditedURI = mainEditedURI.replace(Pattern.quote("{chanel}").toRegex(), "organic")

        //if organic/non-organic chanel
        mainEditedURI = mainEditedURI.replace(Pattern.quote("{geo}").toRegex(), "ru")
        Log.d("FINISH", mainEditedURI)
        return mainEditedURI
    }

}