/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.favorites.fragment.teams


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.db.EntityTeam
import com.ysn.footballclubdicoding.db.database
import com.ysn.footballclubdicoding.favorites.fragment.teams.adapter.AdapterFavoriteTeam
import com.ysn.footballclubdicoding.model.teams.AllTeamItem
import com.ysn.footballclubdicoding.teams.activity.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.longToast

class FavoriteTeamFragment : Fragment(), AnkoLogger {

    private val TAG = javaClass.simpleName
    private var allTeams: MutableList<EntityTeam> = mutableListOf()
    private lateinit var adapterFavoriteTeam: AdapterFavoriteTeam

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doLoadData()
    }

    private fun doLoadData() {
        showLoading()
        recycler_view_fragment_favorite_team.visibility = View.INVISIBLE
        adapterFavoriteTeam = AdapterFavoriteTeam(allTeams = allTeams, context = ctx, listenerAdapterFavoriteTeam = object : AdapterFavoriteTeam.ListenerAdapterFavoriteTeam {
            override fun onClickTeam(entityTeam: EntityTeam) {
                doOnClickItemTeam(entityTeam = entityTeam)
            }
        })
        recycler_view_fragment_favorite_team.layoutManager = LinearLayoutManager(ctx)
        recycler_view_fragment_favorite_team.adapter = adapterFavoriteTeam

        try {
            ctx.database.use {
                val result = select(EntityTeam.TABLE_TEAM)
                val allTeamsDb = result.parseList<EntityTeam>(classParser())
                allTeams.clear()
                allTeams.addAll(allTeamsDb)
                adapterFavoriteTeam.refreshData(allTeams)
                hideLoading()
                recycler_view_fragment_favorite_team.visibility = View.VISIBLE
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            hideLoading()
            recycler_view_fragment_favorite_team.visibility = View.VISIBLE
            longToast(e.localizedMessage)
        }
    }

    private fun doOnClickItemTeam(entityTeam: EntityTeam) {
        info { "entityTeam: $entityTeam" }
        val allTeamItem = AllTeamItem(
                idTeam = entityTeam.idTeam,
                strTeam = entityTeam.strTeam,
                strAlternate = entityTeam.strAlternate,
                intFormedYear = entityTeam.intFormedYear,
                strStadium = entityTeam.strStadium,
                strDescriptionEN = entityTeam.strDescriptionEN,
                strCountry = entityTeam.strCountry,
                strTeamBadge = entityTeam.strTeamBadge
        )
        val intentDetailTeam = ctx.intentFor<DetailTeamActivity>("allTeamItem" to allTeamItem)
        startActivity(intentDetailTeam)
    }

    private fun showLoading() {
        swipe_refresh_layout_fragment_favorite_team.isRefreshing = true
    }

    private fun hideLoading() {
        swipe_refresh_layout_fragment_favorite_team.isRefreshing = false
    }

}
