/*
 * Created by YSN Studio on 5/1/18 5:34 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 5:27 PM
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

    fun searchEventByKeyword(keyword: String): String {
        return "${BuildConfig.BASE_URL}searchevents.php?e=$keyword"
    }

    fun getAllTeamsByLeague(idLeague: String): String {
        return "${BuildConfig.BASE_URL}lookup_all_teams.php?id=$idLeague"
    }

    fun getAllPlayersByTeam(idTeam: String): String {
        return "${BuildConfig.BASE_URL}lookup_all_players.php?id=$idTeam"
    }

    fun searchTeamByKeyword(keyword: String): String {
        return "${BuildConfig.BASE_URL}searchteams.php?t=$keyword"
    }

}