/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding.matches.activitiy.detailmatch

import com.google.gson.Gson
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.api.TheSportDbApi
import com.ysn.footballclubdicoding.model.matches.TeamMatches
import com.ysn.footballclubdicoding.model.matches.TeamsMatches
import com.ysn.footballclubdicoding.util.CoroutineContextProviderTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onLoadImageClub() {
        val teamMatchesHome = ArrayList<TeamMatches>()
        val teamMatchesAway = ArrayList<TeamMatches>()
        val teamMatchHome = TeamsMatches(teamMatchesHome)
        val teamMatchAway = TeamsMatches(teamMatchesAway)
        val idHomeTeam = "133604"
        val idAwayTeam = "133611"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idHomeTeam)),
                TeamsMatches::class.java))
                .thenReturn(teamMatchHome)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idAwayTeam)),
                TeamsMatches::class.java))
                .thenReturn(teamMatchAway)
        presenter.onLoadImageClub(idHomeTeam = idHomeTeam, idAwayTeam = idAwayTeam)
        verify(view).loadImageClub(teamMatchesHome, teamMatchesAway)

    }
}