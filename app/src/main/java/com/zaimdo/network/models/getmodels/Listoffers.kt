package com.zaimdo.network.models.getmodels

data class Listoffers(
    val _id: String,
    val amount: Amount,
    val categories: List<String>,
    val cpa: String,
    val description: String,
    val detail: Detail,
    val img: String,
    val isHidden: Boolean,
    val offer_id: String,
    val offer_name: String,
    val percent: Percent,
    val push: Push,
    val term: Term,
    val time_solution: TimeSolution,
    val top: Boolean,
    val url: String
)