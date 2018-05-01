/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 4:33 AM
 */

package com.ysn.footballclub_dicoding.teams.fragment.playerteam

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.teams.AllPlayers
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailTeamPresenter(private val view: PlayerDetailTeamView,
                                private val apiRepository: ApiRepository,
                                private val gson: Gson,
                                private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onLoadDataAllPlayers(idTeam: String) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getAllPlayersByTeam(idTeam = idTeam)),
                        AllPlayers::class.java)
            }
            view.loadDataAllPlayers(allPlayers = dataApi.await().player)
        }
    }

}
