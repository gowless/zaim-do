package com.zaimdo.network.models.getmodels

data class Data(
    val __v: Int,
    val _id: String,
    val appId: String,
    val categories: List<Category>,
    val listoffers: List<Listoffers>
)