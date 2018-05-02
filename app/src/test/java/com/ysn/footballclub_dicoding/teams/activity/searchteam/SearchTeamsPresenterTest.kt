/*
 * Created by YSN Studio on 5/2/18 8:46 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/2/18 8:40 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.searchteam

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.teams.AllTeamItem
import com.ysn.footballclub_dicoding.model.teams.AllTeams
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import com.ysn.footballclub_dicoding.util.CoroutineContextProviderTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchTeamsPresenterTest {

    @Mock
    private lateinit var view: SearchTeamsView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: SearchTeamsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamsPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onSearchingTeamByClubName() {
        val allTeamItems = ArrayList<AllTeamItem>()
        val response = AllTeams(allTeamItems)
        val keyword = "Arsenal"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.searchTeamByKeyword(keyword = keyword)),
                AllTeams::class.java))
                .thenReturn(response)
        presenter.onSearchingTeamByClubName(keyword = keyword)
        verify(view).searchingTeamByClubName(allTeamItems)
    }
}