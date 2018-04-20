/*
 * Created by YSN Studio on 4/20/18 8:02 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/18/18 10:34 PM
 */

package com.ysn.footballclub_dicoding.nextmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.Endpoints
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.League
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter constructor(private val view: NextMatchView?,
                                     private val endpoints: Endpoints) {

    private lateinit var adapterMatch: AdapterMatch
    private lateinit var events: List<Event>

    fun onLoadData() {
        events = ArrayList()
        adapterMatch = AdapterMatch(events = events, listenerAdapterMatch = object : AdapterMatch.ListenerAdapterMatch {
            override fun onClick(event: Event) {
                view?.onClickItemNextMatch(event = event)
            }
        })
        async(UI) {
            var responseMessage = ""
            val dataApi = bg {
                val response = endpoints.getEventNextLeague().execute()
                responseMessage = response.message()
                when (response.code()) {
                    200 -> Gson().fromJson<League>(response.body()?.string(), League::class.java)
                    else -> null
                }
            }
            when (dataApi.await()) {
                null -> view?.loadDataFailed(message = responseMessage)
                else -> {
                    adapterMatch.refreshData(events = dataApi.await()!!.events as java.util.ArrayList<Event>)
                    view?.loadData(adapterMatch = adapterMatch)
                }
            }
        }
    }

    fun onRefreshData() {
        events = ArrayList()
        async(UI) {
            var responseMessage = ""
            val dataApi = bg {
                val response = endpoints.getEventNextLeague().execute()
                responseMessage = response.message()
                when (response.code()) {
                    200 -> Gson().fromJson(response.body()?.string(), League::class.java)
                    else -> null
                }
            }
            when (dataApi.await()) {
                null -> view?.refreshDataFailed(message = responseMessage)
                else -> {
                    adapterMatch.refreshData(events = dataApi.await()!!.events as java.util.ArrayList<Event>)
                    view?.refreshData()
                }
            }
        }
    }

}
