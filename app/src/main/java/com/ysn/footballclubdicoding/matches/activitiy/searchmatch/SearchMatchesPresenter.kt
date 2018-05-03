/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:15 PM
 */

package com.ysn.footballclubdicoding.matches.activitiy.searchmatch

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.matches.SearchLeagueMatches
import com.ysn.footballclubdicoding.util.CoroutineContextProvider
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
                        SearchLeagueMatches::class.java)
            }
            view.searchEventByClubName(eventMatches = dataApi.await().eventMatches)
        }
    }

}
