/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:15 PM
 */

package com.ysn.footballclubdicoding.matches.fragment.previousmatch

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.matches.EventMatches
import com.ysn.footballclubdicoding.model.matches.LeagueMatches
import com.ysn.footballclubdicoding.util.CoroutineContextProviderTest
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PreviousMatchPresenterTest {

    @Mock
    private lateinit var view: PreviousMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: PreviousMatchPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PreviousMatchPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onLoadDataEventsPastLeague() {
        val eventMatches = ArrayList<EventMatches>()
        val response = LeagueMatches(eventMatches = eventMatches)
        val idLeague = "4328"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventsPastLeague(idLeague = idLeague)),
                LeagueMatches::class.java))
                .thenReturn(response)
        presenter.onLoadDataEventsPastLeague(idLeague = idLeague.toInt())
        verify(view).loadDataEventsPastLeague(response.eventMatches)
    }
}