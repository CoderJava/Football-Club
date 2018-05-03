/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding.matches.activitiy.detailmatch

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.matches.TeamMatches
import com.ysn.footballclubdicoding.model.matches.TeamsMatches
import com.ysn.footballclubdicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter(private val view: DetailMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    private val TAG = javaClass.simpleName

    fun onLoadImageClub(idHomeTeam: String, idAwayTeam: String) {
        async(context.main) {
            val imageHomeTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idHomeTeam)),
                        TeamsMatches::class.java)
            }
            val imageAwayTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idAwayTeam)),
                        TeamsMatches::class.java)
            }
            view.loadImageClub(imageHomeTeam.await().teamMatches as ArrayList<TeamMatches>, imageAwayTeam.await().teamMatches as ArrayList<TeamMatches>)
        }
    }
}