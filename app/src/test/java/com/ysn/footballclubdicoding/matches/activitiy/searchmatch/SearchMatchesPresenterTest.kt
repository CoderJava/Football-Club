/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.matches.activitiy.searchmatch

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.matches.EventSearchLeagueMatches
import com.ysn.footballclubdicoding.model.matches.SearchLeagueMatches
import com.ysn.footballclubdicoding.util.CoroutineContextProviderTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchMatchesPresenterTest {

    @Mock
    private lateinit var view: SearchMatchesView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: SearchMatchesPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchesPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onSearchingEventByClubName() {
        val eventMatches = ArrayList<EventSearchLeagueMatches>()
        val response = SearchLeagueMatches(eventMatches = eventMatches)
        val keyword = "Arsenal"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.searchEventByKeyword(keyword = keyword)),
                SearchLeagueMatches::class.java))
                .thenReturn(response)
        presenter.onSearchingEventByClubName(keyword = keyword)
        verify(view).searchEventByClubName(response.eventMatches)
    }
}