/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 10:54 AM
 */

package com.ysn.footballclub_dicoding.detailmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.Team
import com.ysn.footballclub_dicoding.model.Teams
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter constructor(private val view: DetailMatchView,
                                       private val apiRepository: ApiRepository,
                                       private val gson: Gson,
                                       private val context: CoroutineContextProvider = CoroutineContextProvider()) : AnkoLogger {

    fun onLoadImageClub(idHomeTeam: String, idAwayTeam: String) {
        async(context.main) {
            val imageHomeTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idHomeTeam)),
                        Teams::class.java)
            }
            val imageAwayTeam = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idAwayTeam)),
                        Teams::class.java)
            }
            view.loadImageClub(imageHomeTeam.await().teams as ArrayList<Team>, imageAwayTeam.await().teams as ArrayList<Team>)
        }
    }

}