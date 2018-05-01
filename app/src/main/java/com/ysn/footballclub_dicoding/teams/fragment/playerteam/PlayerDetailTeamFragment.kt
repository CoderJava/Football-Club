/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 3:51 PM
 */

package com.ysn.footballclub_dicoding.teams.fragment.playerteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.model.teams.Player
import com.ysn.footballclub_dicoding.teams.activity.detailplayer.DetailPlayerActivity
import com.ysn.footballclub_dicoding.teams.fragment.playerteam.adapter.AdapterPlayerDetailTeam
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayerDetailTeamFragment : Fragment(), PlayerDetailTeamView {

    private val TAG = javaClass.simpleName
    private var allPlayers: MutableList<Player> = mutableListOf()
    private lateinit var adapterPlayerDetailTeam: AdapterPlayerDetailTeam
    private lateinit var presenter: PlayerDetailTeamPresenter
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var idTeam: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = PlayerDetailTeamUI()
                .createView(AnkoContext.create(ctx, this))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initPresenter()
        setupAdapterPlayerDetailTeam()
        getDataBundle()
        doLoadData()
    }

    private fun initViews() {
        swipeRefreshLayout = find(R.id.swipe_refresh_layout_player_detail_team)
        recyclerView = find(R.id.recycler_view_player_detail_team)
    }

    private fun initPresenter() {
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = PlayerDetailTeamPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun setupAdapterPlayerDetailTeam() {
        adapterPlayerDetailTeam = AdapterPlayerDetailTeam(allPlayer = allPlayers, context = ctx, listenerAdapterPlayerDetailTeam = object : AdapterPlayerDetailTeam.ListenerAdapterPlayerDetailTeam {
            override fun onClickItemPlayer(playerItem: Player) {
                doOnClickitemPlayer(playerItem = playerItem)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapterPlayerDetailTeam
    }

    private fun getDataBundle() {
        idTeam = arguments?.getString("idTeam")!!
    }

    private fun doOnClickitemPlayer(playerItem: Player) {
        val intentDetailPlayer = ctx.intentFor<DetailPlayerActivity>("playerItem" to playerItem)
        startActivity(intentDetailPlayer)
    }

    private fun doLoadData() {
        showLoading()
        presenter.onLoadDataAllPlayers(idTeam = idTeam)
    }

    override fun loadDataAllPlayers(allPlayers: List<Player>) {
        hideLoading()
        this.allPlayers.clear()
        this.allPlayers.addAll(allPlayers)
        adapterPlayerDetailTeam.refreshData(this.allPlayers)
    }

    private fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
        recyclerView.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.visibility = View.VISIBLE
    }

}

class PlayerDetailTeamUI : AnkoComponent<PlayerDetailTeamFragment> {

    override fun createView(ui: AnkoContext<PlayerDetailTeamFragment>): View {
        return with(ui) {
            relativeLayout {

                swipeRefreshLayout {

                    recyclerView {
                        id = R.id.recycler_view_player_detail_team
                        lparams {
                            width = matchParent
                            height = wrapContent
                        }
                    }

                    id = R.id.swipe_refresh_layout_player_detail_team
                    isEnabled = false
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }

                id = R.id.relative_layout_container_player_detail_team
                lparams {
                    width = matchParent
                    height = matchParent
                }

            }
        }
    }

}
