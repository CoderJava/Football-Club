/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 11:11 PM
 */

package com.ysn.footballclubdicoding.matches.activitiy.detailmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.api.ApiRepository
import com.ysn.footballclubdicoding.db.EntityEvent
import com.ysn.footballclubdicoding.db.database
import com.ysn.footballclubdicoding.model.matches.EventMatches
import com.ysn.footballclubdicoding.model.matches.EventSearchLeagueMatches
import com.ysn.footballclubdicoding.model.matches.TeamMatches
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private val TAG = javaClass.simpleName

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var eventMatches: EventMatches
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var menu: Menu
    private var count: Int = 0
    private var isAlreadyDataInLocal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        initPresenter()
        initListeners()
        doLoadData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_match, menu)
        this.menu = menu
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_match).let {
            when (count) {
                0 -> {
                    isAlreadyDataInLocal = false
                    it.setIcon(R.drawable.ic_star_border_white_24dp)
                }
                else -> {
                    isAlreadyDataInLocal = true
                    it.setIcon(R.drawable.ic_star_white_24dp)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_add_favorite_menu_detail_match -> {
                if (isAlreadyDataInLocal) {
                    deleteFavoriteMatch()
                } else {
                    addFavoriteMatch()
                }
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initPresenter() {
        gson = Gson()
        apiRepository = ApiRepository()
        presenter = DetailMatchPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    private fun addFavoriteMatch() {
        database.use {
            insert(EntityEvent.TABLE_EVENT,
                    EntityEvent.ID_EVENT to eventMatches.idEvent,
                    EntityEvent.DATE_EVENT to eventMatches.dateEvent,
                    EntityEvent.ID_HOME_TEAM to eventMatches.idHomeTeam,
                    EntityEvent.ID_AWAY_TEAM to eventMatches.idAwayTeam,
                    EntityEvent.INT_HOME_SCORE to eventMatches.intHomeScore,
                    EntityEvent.INT_AWAY_SCORE to eventMatches.intAwayScore,
                    EntityEvent.STR_HOME_TEAM to eventMatches.strHomeTeam,
                    EntityEvent.STR_AWAY_TEAM to eventMatches.strAwayTeam,
                    EntityEvent.STR_HOME_FORMATION to eventMatches.strHomeFormation,
                    EntityEvent.STR_AWAY_FORMATION to eventMatches.strAwayFormation,
                    EntityEvent.STR_HOME_GOAL_DETAILS to eventMatches.strHomeGoalDetails,
                    EntityEvent.STR_AWAY_GOAL_DETAILS to eventMatches.strAwayGoalDetails,
                    EntityEvent.INT_HOME_SHOTS to eventMatches.intHomeShots,
                    EntityEvent.INT_AWAY_SHOTS to eventMatches.intAwayShots,
                    EntityEvent.STR_HOME_LINEUP_GOAL_KEEPER to eventMatches.strHomeLineupGoalkeeper,
                    EntityEvent.STR_AWAY_LINEUP_GOAL_KEEPER to eventMatches.strAwayLineupGoalkeeper,
                    EntityEvent.STR_HOME_LINEUP_DEFENSE to eventMatches.strHomeLineupDefense,
                    EntityEvent.STR_AWAY_LINEUP_DEFENSE to eventMatches.strAwayLineupDefense,
                    EntityEvent.STR_HOME_LINEUP_MIDFIELD to eventMatches.strHomeLineupMidfield,
                    EntityEvent.STR_AWAY_LINEUP_MIDFIELD to eventMatches.strAwayLineupMidfield,
                    EntityEvent.STR_HOME_LINEUP_FORWARD to eventMatches.strHomeLineupForward,
                    EntityEvent.STR_AWAY_LINEUP_FORWARD to eventMatches.strAwayLineupForward,
                    EntityEvent.STR_HOME_LINEUP_SUBSTITUTES to eventMatches.strHomeLineupSubstitutes,
                    EntityEvent.STR_AWAY_LINEUP_SUBSTITUTES to eventMatches.strAwayLineupSubstitutes
            )
            isAlreadyDataInLocal = true
            menu.findItem(R.id.menu_item_add_favorite_menu_detail_match)
                    .setIcon(R.drawable.ic_star_white_24dp)
            toast("Match has been added to Favorite")
        }
    }

    private fun deleteFavoriteMatch() {
        database.use {
            delete(EntityEvent.TABLE_EVENT,
                    "(${EntityEvent.ID_EVENT} = {idEvent})",
                    "idEvent" to eventMatches.idEvent)
        }
        isAlreadyDataInLocal = false
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_match)
                .setIcon(R.drawable.ic_star_border_white_24dp)
        toast("Match has been removed from Favorite")
    }

    private fun initListeners() {
        swipe_refresh_layout_activity_detail_match.setOnRefreshListener {
            setupData()
        }
    }

    private fun doLoadData() {
        val bundle = intent.extras
        if (bundle.containsKey("eventMatches")) {
            val dataEvent = bundle?.getSerializable("eventMatches")
            if (dataEvent is EventMatches) {
                eventMatches = bundle.getSerializable("eventMatches") as EventMatches
            } else {
                val eventSearchLeague = bundle.getSerializable("eventMatches") as EventSearchLeagueMatches
                eventMatches = EventMatches(
                        idEvent = eventSearchLeague.idEvent,
                        dateEvent = eventSearchLeague.dateEvent,
                        idHomeTeam = eventSearchLeague.idHomeTeam,
                        idAwayTeam = eventSearchLeague.idAwayTeam,
                        intHomeScore = eventSearchLeague.intHomeScore,
                        intAwayScore = eventSearchLeague.intAwayScore,
                        strHomeTeam = eventSearchLeague.strHomeTeam,
                        strAwayTeam = eventSearchLeague.strAwayTeam,
                        strHomeFormation = eventSearchLeague.strHomeFormation,
                        strAwayFormation = eventSearchLeague.strAwayFormation,
                        strHomeGoalDetails = eventSearchLeague.strHomeGoalDetails,
                        strAwayGoalDetails = eventSearchLeague.strAwayGoalDetails,
                        intHomeShots = eventSearchLeague.intHomeShots,
                        intAwayShots = eventSearchLeague.intAwayShots,
                        strHomeLineupGoalkeeper = eventSearchLeague.strHomeLineupGoalkeeper,
                        strAwayLineupGoalkeeper = eventSearchLeague.strAwayLineupGoalkeeper,
                        strHomeLineupDefense = eventSearchLeague.strHomeLineupDefense,
                        strAwayLineupDefense = eventSearchLeague.strAwayLineupDefense,
                        strHomeLineupMidfield = eventSearchLeague.strHomeLineupMidfield,
                        strAwayLineupMidfield = eventSearchLeague.strAwayLineupMidfield,
                        strHomeLineupForward = eventSearchLeague.strHomeLineupForward,
                        strAwayLineupForward = eventSearchLeague.strAwayLineupForward,
                        strHomeLineupSubstitutes = eventSearchLeague.strHomeLineupSubstitutes,
                        strAwayLineupSubstitutes = eventSearchLeague.strAwayLineupSubstitutes
                )
            }
        } else {
            val entityEvent = bundle.getSerializable("entityEvent") as EntityEvent
            eventMatches = EventMatches(
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
        }
        idHomeTeam = eventMatches.idHomeTeam.toString()
        idAwayTeam = eventMatches.idAwayTeam.toString()
        loadDataLocal()
    }

    override fun loadImageClub(homeLogos: ArrayList<TeamMatches>, awayLogos: ArrayList<TeamMatches>) {
        Glide.with(this)
                .load(homeLogos[0].strTeamBadge)
                .into(image_view_home_activity_detail_match)
        Glide.with(this)
                .load(awayLogos[0].strTeamBadge)
                .into(image_view_away_activity_detail_match)
    }

    private fun loadDataLocal() {
        database.use {
            select(EntityEvent.TABLE_EVENT)
                    .whereArgs("(${EntityEvent.ID_EVENT} = {idEvent})",
                            "idEvent" to eventMatches.idEvent).exec {
                        this@DetailMatchActivity.count = count
                        setupData()
                    }
        }
    }

    private fun setupData() {
        showLoading()
        presenter.onLoadImageClub(idHomeTeam = idHomeTeam, idAwayTeam = idAwayTeam)

        // load data score
        text_view_home_score_activity_detail_match.text = eventMatches.intHomeScore
        text_view_away_score_activity_detail_match.text = eventMatches.intAwayScore

        // load data club name
        text_view_home_club_name_activity_detail_match.text = eventMatches.strHomeTeam
        text_view_away_club_name_activity_detail_match.text = eventMatches.strAwayTeam

        // load data formation
        text_view_home_formation_activity_detail_match.text = eventMatches.strHomeFormation
        text_view_away_formation_activity_detail_match.text = eventMatches.strAwayFormation

        // load data goals
        text_view_home_goal_activity_detail_match.text = eventMatches.strHomeGoalDetails?.replace(";", "\n")
        text_view_away_goal_activity_detail_match.text = eventMatches.strAwayGoalDetails?.replace(";", "\n")

        // load data shots
        text_view_home_shot_activity_detail_match.text = eventMatches.intHomeShots
        text_view_away_shot_activity_detail_match.text = eventMatches.intAwayShots

        // load data goalkeeper
        text_view_home_goalkeeper_activity_detail_match.text = eventMatches.strHomeLineupGoalkeeper?.replace(";", "\n")
        text_view_away_goalkeeper_activity_detail_match.text = eventMatches.strAwayLineupGoalkeeper?.replace(";", "\n")

        // load data defense
        text_view_home_defense_activity_detail_match.text = eventMatches.strHomeLineupDefense?.replace(";", "\n")
        text_view_away_defense_activity_detail_match.text = eventMatches.strAwayLineupDefense?.replace(";", "\n")

        // load data midfield
        text_view_home_midfield_activity_detail_match.text = eventMatches.strHomeLineupMidfield?.replace(";", "\n")
        text_view_away_midfield_activity_detail_match.text = eventMatches.strAwayLineupMidfield?.replace(";", "\n")

        // load data forward
        text_view_home_forward_activity_detail_match.text = eventMatches.strHomeLineupForward?.replace(";", "\n")
        text_view_away_forward_activity_detail_match.text = eventMatches.strAwayLineupForward?.replace(";", "\n")

        // load data substitutes
        text_view_home_substitutes_activity_detail_match.text = eventMatches.strHomeLineupSubstitutes?.replace(";", "\n")
        text_view_away_substitutes_activity_detail_match.text = eventMatches.strAwayLineupSubstitutes?.replace(";", "\n")
        hideLoading()
    }

    private fun hideLoading() {
        swipe_refresh_layout_activity_detail_match.isRefreshing = false
        linear_layout_container_content_activity_detail_match.visibility = View.VISIBLE
    }

    private fun showLoading() {
        swipe_refresh_layout_activity_detail_match.isRefreshing = true
        linear_layout_container_content_activity_detail_match.visibility = View.INVISIBLE
    }

}
