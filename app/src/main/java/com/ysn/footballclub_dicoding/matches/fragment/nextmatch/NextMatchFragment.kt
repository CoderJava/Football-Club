/*
 * Created by YSN Studio on 4/26/18 4:50 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 3:39 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.matches.activitiy.detailmatch.DetailMatchActivity
import com.ysn.footballclub_dicoding.matches.fragment.nextmatch.adapter.AdapterNextMatch
import com.ysn.footballclub_dicoding.matches.fragment.selectleaguematch.SelectLeagueMatchActivity
import com.ysn.footballclub_dicoding.model.Event
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx

class NextMatchFragment : Fragment(), NextMatchView {

    private val TAG = javaClass.simpleName
    private val requestCodeSelectLeague = 100
    private var events: MutableList<Event> = mutableListOf()
    private var idLeague = 0
    private lateinit var adapterNextMatch: AdapterNextMatch
    private lateinit var presenter: NextMatchPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout_fragment_next_match.isEnabled = false
        initPresenter()
        initListeners()
        setupAdapterNextMatch()
        doLoadData()
    }

    private fun initPresenter() {
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = NextMatchPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun initListeners() {
        linear_layout_container_league_fragment_next_match.setOnClickListener {
            val intentSelectLeagueMatchActivity = ctx.intentFor<SelectLeagueMatchActivity>("idLeague" to idLeague)
            startActivityForResult(intentSelectLeagueMatchActivity, requestCodeSelectLeague)
        }
    }

    private fun setupAdapterNextMatch() {
        adapterNextMatch = AdapterNextMatch(events = events, listenerAdapterMatch = object : AdapterNextMatch.ListenerAdapterMatch {
            override fun onClickItemMatch(event: Event) {
                doOnClickItemMatch(event = event)
            }
        })
        recycler_view_match_fragment_next_match.layoutManager = LinearLayoutManager(ctx)
        recycler_view_match_fragment_next_match.adapter = adapterNextMatch
    }

    private fun doLoadData() {
        val arrayLeague = resources.getStringArray(R.array.array_league)
        val arrayLeagueId = resources.getIntArray(R.array.array_league_id)
        idLeague = 0
        text_view_league_fragment_next_match.text = arrayLeague[0]

        showLoading()
        presenter.onLoadDataEventsNextLeague(idLeague = arrayLeagueId[0])
    }

    private fun showLoading() {
        swipe_refresh_layout_fragment_next_match.isRefreshing = true
    }

    private fun hideLoading() {
        swipe_refresh_layout_fragment_next_match.isRefreshing = false
    }

    private fun doOnClickItemMatch(event: Event) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("event" to event)
        startActivity(intentDetailMatchActivity)
    }

    override fun loadDataEventsNextLeague(events: List<Event>) {
        hideLoading()
        this.events.addAll(events)
        AnkoLogger(javaClass.simpleName).info { "event.size: ${events.size}" }
        adapterNextMatch.refreshData(this.events)
    }

}
