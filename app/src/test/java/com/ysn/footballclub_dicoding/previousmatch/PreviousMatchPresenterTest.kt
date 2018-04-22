/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:09 AM
 */

package com.ysn.footballclub_dicoding.previousmatch

import com.google.gson.Gson
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.api.TheSportDbApi
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.League
import com.ysn.footballclub_dicoding.util.CoroutineContextProviderTest
import org.junit.Before
import org.junit.Test
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
    fun onLoadData() {
        val events = ArrayList<Event>()
        val response = League(events = events)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDbApi.getEventPastLeague()), League::class.java))
                .thenReturn(response)
        presenter.onLoadData()
        verify(view).loadData(response.events)
    }

}