/*
 * Created by YSN Studio on 5/1/18 5:34 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 5:30 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.searchteam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.gson.Gson
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.model.teams.AllTeamItem
import com.ysn.footballclub_dicoding.teams.activity.detailteam.DetailTeamActivity
import com.ysn.footballclub_dicoding.teams.activity.searchteam.adapter.AdapterSearchTeams
import kotlinx.android.synthetic.main.activity_search_teams.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class SearchTeamsActivity : AppCompatActivity(), SearchTeamsView {

    private val TAG = javaClass.simpleName

    private var allTeams: MutableList<AllTeamItem> = mutableListOf()
    private lateinit var adapterSearchTeams: AdapterSearchTeams
    private lateinit var presenter: SearchTeamsPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_teams)
        initToolbar()
        initPresenter()
        initListener()
        setupAdapterSearchTeams()
    }

    private fun initPresenter() {
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = SearchTeamsPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_activity_search_teams)
    }

    private fun initListener() {
        image_view_clear_keyword_activity_search_teams.setOnClickListener {
            edit_text_keyword_activity_search_teams.text.clear()
        }
        edit_text_keyword_activity_search_teams.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = textView.text.toString().trim()
                if (keyword.isEmpty()) {
                    toast("Keyword is empty")
                } else {
                    showLoading()
                    presenter.onSearchingTeamByClubName(keyword = keyword)
                }
                true
            } else {
                false
            }
        }
    }

    override fun searchingTeamByClubName(allTeams: List<AllTeamItem>) {
        hideLoading()
        this.allTeams.clear()
        this.allTeams.addAll(allTeams)
        adapterSearchTeams.refreshData(this.allTeams)
    }

    private fun showLoading() {
        recycler_view_search_activity_search_teams.visibility = View.INVISIBLE
        swipe_refresh_layout_activity_search_teams.isRefreshing = true
    }

    private fun hideLoading() {
        recycler_view_search_activity_search_teams.visibility = View.VISIBLE
        swipe_refresh_layout_activity_search_teams.isRefreshing = false
    }

    private fun setupAdapterSearchTeams() {
        swipe_refresh_layout_activity_search_teams.isEnabled = false
        adapterSearchTeams = AdapterSearchTeams(allTeams = allTeams, context = ctx, listenerAdapterSearchTeam = object : AdapterSearchTeams.ListenerAdapterSearchTeam {
            override fun onClickItemTeam(allTeamItem: AllTeamItem) {
                doOnClickItemTeam(allTeamItem = allTeamItem)
            }
        })
        recycler_view_search_activity_search_teams.layoutManager = LinearLayoutManager(ctx)
        recycler_view_search_activity_search_teams.adapter = adapterSearchTeams
    }

    private fun doOnClickItemTeam(allTeamItem: AllTeamItem) {
        val intentDetailTeam = ctx.intentFor<DetailTeamActivity>("allTeamItem" to allTeamItem)
        startActivity(intentDetailTeam)
    }

}
