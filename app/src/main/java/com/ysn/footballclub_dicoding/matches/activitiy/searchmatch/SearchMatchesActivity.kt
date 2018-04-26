/*
 * Created by YSN Studio on 4/26/18 11:09 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 11:08 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.searchmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.gson.Gson
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.matches.activitiy.detailmatch.DetailMatchActivity
import com.ysn.footballclub_dicoding.matches.activitiy.searchmatch.adapter.AdapterSearchMatches
import com.ysn.footballclub_dicoding.model.EventSearchLeague
import kotlinx.android.synthetic.main.activity_search_matches.*
import org.jetbrains.anko.*

class SearchMatchesActivity : AppCompatActivity(), SearchMatchesView {

    private val TAG = javaClass.simpleName

    private var events: MutableList<EventSearchLeague> = mutableListOf()
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
        adapterSearchMatches = AdapterSearchMatches(events = events, listenerADapterSearchMatch = object : AdapterSearchMatches.ListenerAdapterSearchesMatches {
            override fun onClickItemMatch(event: EventSearchLeague) {
                doOnClickItemMatch(event = event)
            }
        })
        recycler_view_search_activity_search_matches.layoutManager = LinearLayoutManager(ctx)
        recycler_view_search_activity_search_matches.adapter = adapterSearchMatches
    }

    private fun doOnClickItemMatch(event: EventSearchLeague) {
        val intentDetailMatchActivity = intentFor<DetailMatchActivity>("event" to event)
        startActivity(intentDetailMatchActivity)
    }

    override fun searchEventByClubName(events: List<EventSearchLeague>) {
        AnkoLogger(TAG).info { "searchEventByClubName" }
        hideLoading()
        this.events.clear()
        this.events.addAll(events)
        adapterSearchMatches.refreshData(this.events)
    }

}
