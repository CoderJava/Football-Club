/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 10:56 AM
 */

package com.ysn.footballclub_dicoding.detailmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.model.Team
import com.ysn.footballclub_dicoding.model.Teams
import com.ysn.footballclub_dicoding.util.CoroutineContextProviderTest
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

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view = view, apiRepository = apiRepository, gson = gson, context = CoroutineContextProviderTest())
    }

    @Test
    fun onLoadImageClub() {
        val idHomeTeam = "133611"
        val idAwayTeam = "133602"
        val listHomeTeam = ArrayList<Team>()
        val responseHomeTeam = Teams(listHomeTeam)
        val listAwayTeam = ArrayList<Team>()
        val responseAwayTeam = Teams(listAwayTeam)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idHomeTeam)), Teams::class.java))
                .thenReturn(responseHomeTeam)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailTeam(idTeam = idAwayTeam)), Teams::class.java))
                .thenReturn(responseAwayTeam)
        presenter.onLoadImageClub(idHomeTeam = idHomeTeam, idAwayTeam = idAwayTeam)
        verify(view).loadImageClub(homeLogo = responseHomeTeam.teams, awayLogo = responseAwayTeam.teams)
    }

}