/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 6:11 PM
 */

package com.ysn.footballclub_dicoding.nextmatch

import com.ysn.footballclub_dicoding.api.Endpoints
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        endpoints.getEventNextLeague()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            adapterMatch.refreshData(events = it.events as java.util.ArrayList<Event>)
                            view?.loadData(adapterMatch = adapterMatch)
                        },
                        {
                            it.printStackTrace()
                            view?.loadDataFailed(message = it.message!!)
                        },
                        {
                            /* nothing to do in here */
                        }
                )
    }

    fun onRefreshData() {
        events = ArrayList()
        endpoints.getEventNextLeague()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            adapterMatch.refreshData(events = it.events as java.util.ArrayList<Event>)
                            view?.refreshData()
                        },
                        {
                            it.printStackTrace()
                            view?.refreshDataFailed(message = it.message!!)
                        },
                        {
                            /* nothing to do in here */
                        }
                )
    }

}
