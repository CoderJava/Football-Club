/*
 * Created by YSN Studio on 5/2/18 8:46 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/2/18 8:45 PM
 */

package com.ysn.footballclub_dicoding.teams.fragment.playerteam

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.teams.AllPlayers
import com.ysn.footballclub_dicoding.model.teams.Player
import com.ysn.footballclub_dicoding.util.CoroutineContextProvider
import com.ysn.footballclub_dicoding.util.CoroutineContextProviderTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerDetailTeamPresenterTest {

    @Mock
    private lateinit var view: PlayerDetailTeamView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: PlayerDetailTeamPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailTeamPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onLoadDataAllPlayers() {
        val players = ArrayList<Player>()
        val response = AllPlayers(players)
        val idTeam = "133611"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getAllPlayersByTeam(idTeam = idTeam)),
                AllPlayers::class.java))
                .thenReturn(response)
        presenter.onLoadDataAllPlayers(idTeam = idTeam)
        verify(view).loadDataAllPlayers(response.player)
    }
}