package com.kotlin_base_dev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin_base_dev.adapters.ZeroListAdapter
import com.kotlin_base_dev.uiactivities.Splash
import com.zaimdo.R


class ZeroList : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_zero_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val adapterZeroList = ZeroListAdapter(Splash.iteratedListZero)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapterZeroList
        return view;
    }



    }
