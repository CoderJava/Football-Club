/*
 * Created by YSN Studio on 5/1/18 5:34 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 5:30 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.searchteam

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.teams.AllTeams
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamsPresenter(private val view: SearchTeamsView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onSearchingTeamByClubName(keyword: String) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.searchTeamByKeyword(keyword = keyword)),
                        AllTeams::class.java)
            }
            view.searchingTeamByClubName(allTeams = dataApi.await()!!.allTeamItems)
        }
    }

}
