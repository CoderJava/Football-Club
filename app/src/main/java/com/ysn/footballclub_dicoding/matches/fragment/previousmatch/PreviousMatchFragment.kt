/*
 * Created by YSN Studio on 4/26/18 4:50 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 4:49 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.previousmatch


import android.app.Activity
import android.content.Intent
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
import com.ysn.footballclub_dicoding.matches.fragment.previousmatch.adapter.AdapterPreviousMatch
import com.ysn.footballclub_dicoding.matches.fragment.selectleaguematch.SelectLeagueMatchActivity
import com.ysn.footballclub_dicoding.model.Event
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx

class PreviousMatchFragment : Fragment(), PreviousMatchView {

    private val TAG = javaClass.simpleName
    private val requestCodeSelectLeague = 100
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
            val intentSelectLeagueMatchActivity = ctx.intentFor<SelectLeagueMatchActivity>()
            startActivityForResult(intentSelectLeagueMatchActivity, requestCodeSelectLeague)
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
        startActivity(intentDetailMatchActivity)
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
        recycler_view_match_fragment_previous_match.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        swipe_refresh_layout_fragment_previous_match.isRefreshing = false
        recycler_view_match_fragment_previous_match.visibility = View.VISIBLE
    }

    override fun loadDataEventsPastLeague(events: List<Event>) {
        hideLoading()
        this.events.clear()
        this.events.addAll(events)
        adapterPreviousMatch.refreshData(this.events)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == requestCode) {
            val idLeague = data?.getIntExtra("idLeague", 0)
            val leagueName = data?.getStringExtra("leagueName")
            AnkoLogger(TAG).info { "idLeague: $idLeague" }
            text_view_league_fragment_previous_match.text = leagueName
            showLoading()
            presenter.onLoadDataEventsPastLeague(idLeague = idLeague!!)
        }
    }

}
