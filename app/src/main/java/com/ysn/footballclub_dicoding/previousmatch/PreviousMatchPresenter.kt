/*
 * Created by YSN Studio on 4/20/18 8:02 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/20/18 8:01 AM
 */

package com.ysn.footballclub_dicoding.previousmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.Endpoints
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.League
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg

class PreviousMatchPresenter constructor(private val view: PreviousMatchView?,
                                         private val endpoints: Endpoints) : AnkoLogger {

    private lateinit var adapterMatch: AdapterMatch
    private lateinit var events: List<Event>

    fun onLoadData() {
        events = ArrayList()
        adapterMatch = AdapterMatch(events = events, listenerAdapterMatch = object : AdapterMatch.ListenerAdapterMatch {
            override fun onClick(event: Event) {
                view?.onClickItemPreviousMatch(event = event)
            }
        })

        async(UI) {
            val dataApi = bg {
                val response = endpoints.getEventPastLeague().execute()
                Gson().fromJson<League>(response.body()?.string(), League::class.java)
            }
            adapterMatch.refreshData(events = dataApi.await()!!.events as java.util.ArrayList<Event>)
            view?.loadData(adapterMatch = adapterMatch)
        }
    }

    fun onRefreshData() {
        events = ArrayList()
        async(UI) {
            val dataApi = bg {
                val response = endpoints.getEventPastLeague().execute()
                Gson().fromJson<League>(response.body()?.string(), League::class.java)
            }
            adapterMatch.refreshData(events = dataApi.await()!!.events as java.util.ArrayList<Event>)
            view?.refreshData()
        }
    }

}