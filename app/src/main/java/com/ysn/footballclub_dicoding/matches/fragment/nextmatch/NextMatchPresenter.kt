/*
 * Created by YSN Studio on 4/26/18 3:27 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 3:02 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.League
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
                        League::class.java)
            }
            view.loadDataEventsNextLeague(events = dataApi.await().events)
        }
    }

}
