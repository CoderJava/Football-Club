/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding.api

import com.ysn.footballclubdicoding.BuildConfig

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