/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:15 PM
 */

package com.ysn.footballclubdicoding.matches.fragment.previousmatch

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.matches.LeagueMatches
import com.ysn.footballclubdicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PreviousMatchPresenter constructor(private val view: PreviousMatchView,
                                         private val apiRepository: ApiRepository,
                                         private val gson: Gson,
                                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onLoadDataEventsPastLeague(idLeague: Int?) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventsPastLeague(idLeague = idLeague.toString())),
                        LeagueMatches::class.java)
            }
            view.loadDataEventsPastLeague(eventMatches = dataApi.await().eventMatches)
        }
    }
}