/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.teams


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
import com.ysn.footballclubdicoding.model.teams.AllTeamItem
import com.ysn.footballclubdicoding.selectleague.SelectLeagueMatchActivity
import com.ysn.footballclubdicoding.teams.adapter.AdapterTeams
import com.ysn.footballclubdicoding.teams.activity.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor

class TeamsFragment : Fragment(), TeamsView, AnkoLogger {

    private val TAG = javaClass.simpleName
    private val requestCodeSelectLeague = 100
    private var allTeams: MutableList<AllTeamItem> = mutableListOf()
    private lateinit var adapterTeams: AdapterTeams
    private lateinit var presenter: TeamsPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_Layout_fragment_teams.isEnabled = false
        initPresenter()
        initListeners()
        setupAdapterTeams()
        doLoadData()
    }

    private fun initListeners() {
        linear_layout_container_league_fragment_teams.setOnClickListener {
            val intentSelectLeagueMatchActivity = intentFor<SelectLeagueMatchActivity>()
            startActivityForResult(intentSelectLeagueMatchActivity, requestCodeSelectLeague)
        }
    }

    private fun initPresenter() {
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = TeamsPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun setupAdapterTeams() {
        adapterTeams = AdapterTeams(allTeams = allTeams, context = ctx, listenerAdapterTeam = object : AdapterTeams.ListenerAdapterTeam {
            override fun onClickItemTeam(allTeamItem: AllTeamItem) {
                doOnClickItemTeam(allTeamItem = allTeamItem)
            }
        })
        recycler_view_team_fragment_teams.layoutManager = LinearLayoutManager(ctx)
        recycler_view_team_fragment_teams.adapter = adapterTeams
    }

    private fun doOnClickItemTeam(allTeamItem: AllTeamItem) {
        val intentDetailTeam = ctx.intentFor<DetailTeamActivity>("allTeamItem" to allTeamItem)
        startActivity(intentDetailTeam)
    }

    private fun doLoadData() {
        val arrayLeague = resources.getStringArray(R.array.array_league)
        val arrayLeagueId = resources.getIntArray(R.array.array_league_id)
        text_view_league_fragment_teams.text = arrayLeague[0]

        showLoading()
        presenter.onLoadDataAllTeams(idLeague = arrayLeagueId[0])
    }

    override fun loadDataAllTeams(allTeams: List<AllTeamItem>) {
        hideLoading()
        this.allTeams.clear()
        this.allTeams.addAll(allTeams)
        adapterTeams.refreshData(this.allTeams)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == requestCodeSelectLeague) {
            val idLeague = data?.getIntExtra("idLeague", 0)
            val leagueName = data?.getStringExtra("leagueName")
            text_view_league_fragment_teams.text = leagueName
            showLoading()
            presenter.onLoadDataAllTeams(idLeague = idLeague)
        }
    }

    private fun showLoading() {
        swipe_refresh_Layout_fragment_teams.isRefreshing = true
        recycler_view_team_fragment_teams.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        swipe_refresh_Layout_fragment_teams.isRefreshing = false
        recycler_view_team_fragment_teams.visibility = View.VISIBLE
    }

}
