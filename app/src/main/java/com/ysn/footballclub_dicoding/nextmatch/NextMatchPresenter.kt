/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:06 AM
 */

package com.ysn.footballclub_dicoding.nextmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.League
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter constructor(private val view: NextMatchView?,
                                     private val apiRepository: ApiRepository,
                                     private val gson: Gson,
                                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private lateinit var events: List<Event>

    fun onLoadData() {
        events = ArrayList()
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventNextLeague()), League::class.java)
            }
            view?.loadData(events = dataApi.await()!!.events)
        }
    }

}
