/*
 * Created by YSN Studio on 4/20/18 8:02 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/18/18 10:44 PM
 */

package com.ysn.footballclub_dicoding.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("eventspastleague.php?id=4328")
    fun getEventPastLeague(): Call<ResponseBody>

    @GET("eventsnextleague.php?id=4328")
    fun getEventNextLeague(): Call<ResponseBody>

}