/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.favorites.fragment.matches


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.db.EntityEvent
import com.ysn.footballclubdicoding.db.database
import com.ysn.footballclubdicoding.favorites.fragment.matches.adapter.AdapterFavoriteMatch
import com.ysn.footballclubdicoding.matches.activitiy.detailmatch.DetailMatchActivity
import com.ysn.footballclubdicoding.model.matches.EventMatches
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.longToast

class FavoriteMatchFragment : Fragment() {

    private val TAG = javaClass.simpleName
    private var eventMatches: MutableList<EntityEvent> = mutableListOf()
    private lateinit var adapterFavoriteMatch: AdapterFavoriteMatch

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doLoadData()
    }

    private fun doLoadData() {
        showLoading()
        recycler_view_fragment_favorite_match.visibility = View.INVISIBLE
        adapterFavoriteMatch = AdapterFavoriteMatch(eventMatches = eventMatches, listenerAdapterFavoriteMatch = object : AdapterFavoriteMatch.ListenerAdapterFavoriteMatch {
            override fun onClickItemMatch(eventMatches: EntityEvent) {
                doOnClickItemMatch(entityEvent = eventMatches)
            }
        })
        recycler_view_fragment_favorite_match.layoutManager = LinearLayoutManager(ctx)
        recycler_view_fragment_favorite_match.adapter = adapterFavoriteMatch

        try {
            ctx.database.use {
                val result = select(EntityEvent.TABLE_EVENT)
                val eventDb = result.parseList<EntityEvent>(classParser())
                eventMatches.clear()
                eventMatches.addAll(eventDb)
                adapterFavoriteMatch.refreshData(eventMatches)
                hideLoading()
                recycler_view_fragment_favorite_match.visibility = View.VISIBLE
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            hideLoading()
            recycler_view_fragment_favorite_match.visibility = View.VISIBLE
            longToast(e.localizedMessage)
        }
    }

    private fun doOnClickItemMatch(entityEvent: EntityEvent) {
        val eventMatches = EventMatches(
                idEvent = entityEvent.idEvent!!,
                dateEvent = entityEvent.dateEvent,
                idHomeTeam = entityEvent.idHomeTeam,
                idAwayTeam = entityEvent.idAwayTeam,
                intHomeScore = entityEvent.intHomeScore,
                intAwayScore = entityEvent.intAwayScore,
                strHomeTeam = entityEvent.strHomeTeam,
                strAwayTeam = entityEvent.strAwayTeam,
                strHomeFormation = entityEvent.strHomeFormation,
                strAwayFormation = entityEvent.strAwayFormation,
                strHomeGoalDetails = entityEvent.strHomeGoalDetails,
                strAwayGoalDetails = entityEvent.strAwayGoalDetails,
                intHomeShots = entityEvent.intHomeShots,
                intAwayShots = entityEvent.intAwayShots,
                strHomeLineupGoalkeeper = entityEvent.strHomeLineupGoalKeeper,
                strAwayLineupGoalkeeper = entityEvent.strAwayLineupGoalKeeper,
                strHomeLineupDefense = entityEvent.strHomeLineupDefense,
                strAwayLineupDefense = entityEvent.strAwayLineupDefense,
                strHomeLineupMidfield = entityEvent.strHomeLineupMidfield,
                strAwayLineupMidfield = entityEvent.strAwayLineupMidfield,
                strHomeLineupForward = entityEvent.strHomeLineupForward,
                strAwayLineupForward = entityEvent.strAwayLineupForward,
                strHomeLineupSubstitutes = entityEvent.strHomeLineupSubstitutes,
                strAwayLineupSubstitutes = entityEvent.strAwayLineupSubstitutes
        )
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("eventMatches" to eventMatches)
        startActivity(intentDetailMatchActivity)
    }

    private fun showLoading() {
        swipe_refresh_layout_fragment_favorite_match.isRefreshing = true
    }

    private fun hideLoading() {
        swipe_refresh_layout_fragment_favorite_match.isRefreshing = false
    }

}
