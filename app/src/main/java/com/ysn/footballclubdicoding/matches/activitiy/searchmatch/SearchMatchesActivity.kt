/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding.matches.activitiy.searchmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.gson.Gson
import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.matches.activitiy.detailmatch.DetailMatchActivity
import com.ysn.footballclubdicoding.matches.activitiy.searchmatch.adapter.AdapterSearchMatches
import com.ysn.footballclubdicoding.model.matches.EventSearchLeagueMatches
import kotlinx.android.synthetic.main.activity_search_matches.*
import org.jetbrains.anko.*

class SearchMatchesActivity : AppCompatActivity(), SearchMatchesView {

    private val TAG = javaClass.simpleName

    private var eventMatches: MutableList<EventSearchLeagueMatches> = mutableListOf()
    private lateinit var adapterSearchMatches: AdapterSearchMatches
    private lateinit var presenter: SearchMatchesPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_matches)
        initToolbar()
        initPresenter()
        initListener()
        setupAdapterSearchMatches()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_activity_search_matches)
    }

    private fun initPresenter() {
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = SearchMatchesPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun initListener() {
        image_view_clear_keyword_activity_search_matches.setOnClickListener {
            edit_text_keyword_activity_search_matches.text.clear()
        }
        edit_text_keyword_activity_search_matches.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = textView.text.toString().trim()
                if (keyword.isEmpty()) {
                    toast("Keyword is empty")
                } else {
                    showLoading()
                    presenter.onSearchingEventByClubName(keyword = keyword)
                }
                true
            } else {
                false
            }
        }
    }

    private fun showLoading() {
        recycler_view_search_activity_search_matches.visibility = View.INVISIBLE
        swipe_refresh_layout_activity_search_matches.isRefreshing = true
    }

    private fun hideLoading() {
        recycler_view_search_activity_search_matches.visibility = View.VISIBLE
        swipe_refresh_layout_activity_search_matches.isRefreshing = false
    }

    private fun setupAdapterSearchMatches() {
        swipe_refresh_layout_activity_search_matches.isEnabled = false
        adapterSearchMatches = AdapterSearchMatches(eventMatches = eventMatches, listenerADapterSearchMatch = object : AdapterSearchMatches.ListenerAdapterSearchesMatches {
            override fun onClickItemMatch(eventMatches: EventSearchLeagueMatches) {
                doOnClickItemMatch(eventMatches = eventMatches)
            }
        })
        recycler_view_search_activity_search_matches.layoutManager = LinearLayoutManager(ctx)
        recycler_view_search_activity_search_matches.adapter = adapterSearchMatches
    }

    private fun doOnClickItemMatch(eventMatches: EventSearchLeagueMatches) {
        val intentDetailMatchActivity = intentFor<DetailMatchActivity>("eventMatches" to eventMatches)
        startActivity(intentDetailMatchActivity)
    }

    override fun searchEventByClubName(eventMatches: List<EventSearchLeagueMatches>) {
        AnkoLogger(TAG).info { "searchEventByClubName" }
        hideLoading()
        this.eventMatches.clear()
        this.eventMatches.addAll(eventMatches)
        adapterSearchMatches.refreshData(this.eventMatches)
    }

}
