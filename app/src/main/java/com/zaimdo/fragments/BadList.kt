package com.kotlin_base_dev.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin_base_dev.adapters.AllOffersAdapter
import com.kotlin_base_dev.uiactivities.Splash
import com.zaimdo.R


class BadList : Fragment() {

    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_with_bad_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val adapterAllMain = AllOffersAdapter(Splash.listDataAll)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapterAllMain

        return view;
    }

    }
