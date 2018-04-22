/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 10:28 AM
 */

package com.ysn.footballclub_dicoding.api

import com.ysn.footballclub_dicoding.BuildConfig

object TheSportDbApi {

    fun getEventPastLeague(): String {
        return "${BuildConfig.BASE_URL}eventspastleague.php?id=4328"
    }

    fun getEventNextLeague(): String {
        return "${BuildConfig.BASE_URL}eventsnextleague.php?id=4328"
    }

    fun getDetailTeam(idTeam: String): String {
        return "${BuildConfig.BASE_URL}lookupteam.php?id=$idTeam"
    }

}