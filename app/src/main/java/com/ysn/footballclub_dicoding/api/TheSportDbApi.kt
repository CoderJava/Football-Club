/*
 * Created by YSN Studio on 4/26/18 11:09 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 10:23 PM
 */

package com.ysn.footballclub_dicoding.api

import com.ysn.footballclub_dicoding.BuildConfig
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

object TheSportDbApi {

    fun getEventsPastLeague(idLeague: String): String {
        return "${BuildConfig.BASE_URL}eventspastleague.php?id=$idLeague"
    }

    fun getEventsNextLeague(idLeague: String): String {
        return "${BuildConfig.BASE_URL}eventsnextleague.php?id=$idLeague"
    }

    fun getDetailTeam(idTeam: String): String {
        return "${BuildConfig.BASE_URL}lookupteam.php?id=$idTeam"
    }

    fun searchEventByKeyword(keyword: String): String {
        val endpoint = "${BuildConfig.BASE_URL}searchevents.php?e=$keyword"
        AnkoLogger(javaClass.simpleName).info { "endpoint: $endpoint" }
        return endpoint
    }

}