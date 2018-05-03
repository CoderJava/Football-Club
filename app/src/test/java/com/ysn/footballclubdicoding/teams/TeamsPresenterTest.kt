/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.teams

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.teams.AllTeamItem
import com.ysn.footballclubdicoding.model.teams.AllTeams
import com.ysn.footballclubdicoding.util.CoroutineContextProviderTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {

    @Mock
    private lateinit var view: TeamsFragment

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onLoadDataAllTeams() {
        val allTeamItems = ArrayList<AllTeamItem>()
        val response = AllTeams(allTeamItems)
        val idLeague = "4328"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getAllTeamsByLeague(idLeague = idLeague)),
                AllTeams::class.java))
                .thenReturn(response)
        presenter.onLoadDataAllTeams(idLeague = idLeague.toInt())
        verify(view).loadDataAllTeams(response.allTeamItems)
    }
}