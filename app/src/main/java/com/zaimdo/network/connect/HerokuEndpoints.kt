package com.example.myapplication5

import com.zaimdo.network.models.getmodels.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HerokuEndpoints {

    //query for offers
    @GET("/{region}/{appid}")
    fun getData(
            @Path("region") region: String?,
            @Path("appid") appId: String?
    ): Call<Data>

}