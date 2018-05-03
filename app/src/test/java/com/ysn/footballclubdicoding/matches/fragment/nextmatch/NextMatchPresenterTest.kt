/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:15 PM
 */

package com.ysn.footballclubdicoding.matches.fragment.nextmatch

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

class NextMatchPresenterTest {

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onLoadDataEventsNextLeague() {
        val eventMatches = ArrayList<EventMatches>()
        val response = LeagueMatches(eventMatches = eventMatches)
        val idLeague = "4328"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventsNextLeague(idLeague = idLeague)),
                LeagueMatches::class.java))
                .thenReturn(response)
        presenter.onLoadDataEventsNextLeague(idLeague = idLeague.toInt())
        verify(view).loadDataEventsNextLeague(response.eventMatches)
    }
}