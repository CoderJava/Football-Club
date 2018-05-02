/*
 * Created by YSN Studio on 5/2/18 8:46 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/2/18 5:03 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.searchmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.matches.EventMatches
import com.ysn.footballclub_dicoding.model.matches.EventSearchLeagueMatches
import com.ysn.footballclub_dicoding.model.matches.LeagueMatches
import com.ysn.footballclub_dicoding.model.matches.SearchLeagueMatches
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import com.ysn.footballclub_dicoding.util.CoroutineContextProviderTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
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