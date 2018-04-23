/*
 * Created by YSN Studio on 4/24/18 1:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/24/18 12:55 AM
 */

package com.ysn.footballclub_dicoding.api

import com.ysn.footballclub_dicoding.BuildConfig

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

}