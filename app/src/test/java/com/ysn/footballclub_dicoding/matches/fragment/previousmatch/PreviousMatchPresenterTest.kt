/*
 * Created by YSN Studio on 5/2/18 8:46 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/2/18 3:20 PM
 */

package com.ysn.footballclub_dicoding.matches.fragment.previousmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.matches.EventMatches
import com.ysn.footballclub_dicoding.model.matches.LeagueMatches
import com.ysn.footballclub_dicoding.util.CoroutineContextProviderTest
import org.junit.Test

import org.junit.Assert.*
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