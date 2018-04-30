/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.detailmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.matches.TeamMatches
import com.ysn.footballclub_dicoding.model.matches.TeamsMatches
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
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