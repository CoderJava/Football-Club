/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.fragment.previousmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.matches.LeagueMatches
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PreviousMatchPresenter constructor(private val view: PreviousMatchFragment,
                                         private val apiRepository: ApiRepository,
                                         private val gson: Gson,
                                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onLoadDataEventsPastLeague(idLeague: Int) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventsPastLeague(idLeague = idLeague.toString())),
                        LeagueMatches::class.java)
            }
            view.loadDataEventsPastLeague(eventMatches = dataApi.await().eventMatches)
        }
    }
}