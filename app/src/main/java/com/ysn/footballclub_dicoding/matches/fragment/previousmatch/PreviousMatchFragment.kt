/*
 * Created by YSN Studio on 4/26/18 3:27 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 3:13 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.previousmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.matches.activitiy.detailmatch.DetailMatchActivity
import com.ysn.footballclub_dicoding.matches.fragment.previousmatch.adapter.AdapterPreviousMatch
import com.ysn.footballclub_dicoding.model.Event
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx

class PreviousMatchFragment : Fragment(), PreviousMatchView {

    private val TAG = javaClass.simpleName
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapterPreviousMatch: AdapterPreviousMatch
    private lateinit var presenter: PreviousMatchPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_previous_match, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout_fragment_previous_match.isEnabled = false
        initPresenter()
        initListeners()
        setupAdapterPreviousMatch()
        doLoadData()
    }

    private fun initListeners() {
        linear_layout_container_league_fragment_previous_match.setOnClickListener {
            /* nothing to do in here */
        }
    }

    private fun initPresenter() {
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = PreviousMatchPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun setupAdapterPreviousMatch() {
        adapterPreviousMatch = AdapterPreviousMatch(events = events, listenerAdapterMatch = object : AdapterPreviousMatch.ListenerAdapterMatch {
            override fun onClickItemMatch(event: Event) {
                doOnClickItemMatch(event = event)
            }
        })
        recycler_view_match_fragment_previous_match.layoutManager = LinearLayoutManager(ctx)
        recycler_view_match_fragment_previous_match.adapter = adapterPreviousMatch
    }

    private fun doOnClickItemMatch(event: Event) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("event" to event)
        ctx.startActivity(intentDetailMatchActivity)
    }

    private fun doLoadData() {
        val arrayLeague = resources.getStringArray(R.array.array_league)
        val arrayLeagueId = resources.getIntArray(R.array.array_league_id)
        text_view_league_fragment_previous_match.text = arrayLeague[0]

        showLoading()
        presenter.onLoadDataEventsPastLeague(idLeague = arrayLeagueId[0])
    }

    private fun showLoading() {
        swipe_refresh_layout_fragment_previous_match.isRefreshing = true
    }

    private fun hideLoading() {
        swipe_refresh_layout_fragment_previous_match.isRefreshing = false
    }

    override fun loadDataEventsPastLeague(events: List<Event>) {
        hideLoading()
        this.events.addAll(events)
        adapterPreviousMatch.refreshData(this.events)
    }

}
