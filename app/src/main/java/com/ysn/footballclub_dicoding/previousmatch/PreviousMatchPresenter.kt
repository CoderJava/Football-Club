/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:09 AM
 */

package com.ysn.footballclub_dicoding.previousmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.League
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg

class PreviousMatchPresenter constructor(private val view: PreviousMatchView?,
                                         private val apiRepository: ApiRepository,
                                         private val gson: Gson,
                                         private val context: CoroutineContextProvider = CoroutineContextProvider()) : AnkoLogger {

    private lateinit var events: List<Event>

    fun onLoadData() {
        events = ArrayList()
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventPastLeague()),
                        League::class.java)
            }
            view?.loadData(events = dataApi.await()!!.events as ArrayList<Event>)
        }
    }

}