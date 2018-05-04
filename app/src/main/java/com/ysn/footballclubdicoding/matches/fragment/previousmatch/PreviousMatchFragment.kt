/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.matches.fragment.previousmatch


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.matches.activitiy.detailmatch.DetailMatchActivity
import com.ysn.footballclubdicoding.matches.fragment.previousmatch.adapter.AdapterPreviousMatch
import com.ysn.footballclubdicoding.selectleague.SelectLeagueMatchActivity
import com.ysn.footballclubdicoding.model.matches.EventMatches
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx

class PreviousMatchFragment : Fragment(), PreviousMatchView, AnkoLogger {

    private val TAG = javaClass.simpleName
    private val requestCodeSelectLeague = 100
    private var eventMatches: MutableList<EventMatches> = mutableListOf()
    private lateinit var adapterPreviousMatch: AdapterPreviousMatch
    private lateinit var presenter: PreviousMatchPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
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
        adapterPreviousMatch = AdapterPreviousMatch(eventMatches = eventMatches, listenerAdapterMatch = object : AdapterPreviousMatch.ListenerAdapterMatch {
            override fun onClickItemMatch(eventMatches: EventMatches) {
                doOnClickItemMatch(eventMatches = eventMatches)
            }
        })
        recycler_view_match_fragment_previous_match.layoutManager = LinearLayoutManager(ctx)
        recycler_view_match_fragment_previous_match.adapter = adapterPreviousMatch
    }

    private fun doOnClickItemMatch(eventMatches: EventMatches) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("eventMatches" to eventMatches)
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

    override fun loadDataEventsPastLeague(eventMatches: List<EventMatches>) {
        hideLoading()
        this.eventMatches.clear()
        this.eventMatches.addAll(eventMatches)
        adapterPreviousMatch.refreshData(this.eventMatches)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == requestCodeSelectLeague) {
            val idLeague = data?.getIntExtra("idLeague", 0)
            val leagueName = data?.getStringExtra("leagueName")
            text_view_league_fragment_previous_match.text = leagueName
            showLoading()
            presenter.onLoadDataEventsPastLeague(idLeague = idLeague)
        }
    }

}