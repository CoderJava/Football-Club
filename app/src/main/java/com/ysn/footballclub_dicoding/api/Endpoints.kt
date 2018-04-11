/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 5:55 PM
 */

package com.ysn.footballclub_dicoding.api

import com.ysn.footballclub_dicoding.model.League
import io.reactivex.Observable
import retrofit2.http.GET

interface Endpoints {

    @GET("eventspastleague.php?id=4328")
    fun getEventPastLeague(): Observable<League>

    @GET("eventsnextleague.php?id=4328")
    fun getEventNextLeague(): Observable<League>

}