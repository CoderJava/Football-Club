/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.matches.LeagueMatches
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onLoadDataEventsNextLeague(idLeague: Int) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventsNextLeague(idLeague = idLeague.toString())),
                        LeagueMatches::class.java)
            }
            view.loadDataEventsNextLeague(eventMatches = dataApi.await().eventMatches)
        }
    }

}
