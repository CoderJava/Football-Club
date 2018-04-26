/*
 * Created by YSN Studio on 4/26/18 11:09 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 10:36 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.searchmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.League
import com.ysn.footballclub_dicoding.model.SearchLeague
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchesPresenter(private val view: SearchMatchesView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onSearchingEventByClubName(keyword: String) {
        async(context.main) {
            val dataApi = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.searchEventByKeyword(keyword = keyword)),
                        SearchLeague::class.java)
            }
            view.searchEventByClubName(events = dataApi.await()!!.event)
        }
    }

}
