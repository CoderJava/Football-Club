/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 10:03 PM
 */

package com.ysn.footballclub_dicoding.teams

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.teams.AllTeams
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onLoadDataAllTeams(idLeague: Int) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getAllTeamsByLeague(idLeague = idLeague.toString())),
                        AllTeams::class.java)
            }
            view.loadDataAllTeams(allTeams = dataApi.await().allTeamItems)
        }
    }

}
