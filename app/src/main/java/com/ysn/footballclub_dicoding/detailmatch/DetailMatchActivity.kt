/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 10:58 AM
 */

package com.ysn.footballclub_dicoding.detailmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.db.EntityEvent
import com.ysn.footballclub_dicoding.db.database
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.Team
import com.ysn.footballclub_dicoding.model.Teams
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailMatchView, AnkoLogger {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var event: Event
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var menu: Menu
    private var count: Int = 0
    private var isAlreadyDataInLocal: Boolean = false

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

    private fun addFavoriteMatch() {
        database.use {
            insert(EntityEvent.TABLE_EVENT,
                    EntityEvent.ID_EVENT to event.idEvent,
                    EntityEvent.DATE_EVENT to event.dateEvent,
                    EntityEvent.ID_HOME_TEAM to event.idHomeTeam,
                    EntityEvent.ID_AWAY_TEAM to event.idAwayTeam,
                    EntityEvent.INT_HOME_SCORE to event.intHomeScore,
                    EntityEvent.INT_AWAY_SCORE to event.intAwayScore,
                    EntityEvent.STR_HOME_TEAM to event.strHomeTeam,
                    EntityEvent.STR_AWAY_TEAM to event.strAwayTeam,
                    EntityEvent.STR_HOME_FORMATION to event.strHomeFormation,
                    EntityEvent.STR_AWAY_FORMATION to event.strAwayFormation,
                    EntityEvent.STR_HOME_GOAL_DETAILS to event.strHomeGoalDetails,
                    EntityEvent.STR_AWAY_GOAL_DETAILS to event.strAwayGoalDetails,
                    EntityEvent.INT_HOME_SHOTS to event.intHomeShots,
                    EntityEvent.INT_AWAY_SHOTS to event.intAwayShots,
                    EntityEvent.STR_HOME_LINEUP_GOAL_KEEPER to event.strHomeLineupGoalkeeper,
                    EntityEvent.STR_AWAY_LINEUP_GOAL_KEEPER to event.strAwayLineupGoalkeeper,
                    EntityEvent.STR_HOME_LINEUP_DEFENSE to event.strHomeLineupDefense,
                    EntityEvent.STR_AWAY_LINEUP_DEFENSE to event.strAwayLineupDefense,
                    EntityEvent.STR_HOME_LINEUP_MIDFIELD to event.strHomeLineupMidfield,
                    EntityEvent.STR_AWAY_LINEUP_MIDFIELD to event.strAwayLineupMidfield,
                    EntityEvent.STR_HOME_LINEUP_FORWARD to event.strHomeLineupForward,
                    EntityEvent.STR_AWAY_LINEUP_FORWARD to event.strAwayLineupForward,
                    EntityEvent.STR_HOME_LINEUP_SUBSTITUTES to event.strHomeLineupSubstitutes,
                    EntityEvent.STR_AWAY_LINEUP_SUBSTITUTES to event.strAwayLineupSubstitutes
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
                    "idEvent" to event.idEvent)
        }
        isAlreadyDataInLocal = false
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_match)
                .setIcon(R.drawable.ic_star_border_white_24dp)
        toast("Match has been removed from Favorite")
    }

    private fun doLoadData() {
        showLoading()
        val bundle = intent.extras
        if (bundle.containsKey("event")) {
            event = bundle?.getSerializable("event") as Event
        } else {
            val entityEvent = bundle.getSerializable("entityEvent") as EntityEvent
            event = Event(
                    idEvent = entityEvent.idEvent!!,
                    dateEvent = entityEvent.dateEvent!!,
                    idHomeTeam = entityEvent.idHomeTeam!!,
                    idAwayTeam = entityEvent.idAwayTeam!!,
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
        idHomeTeam = event.idHomeTeam
        idAwayTeam = event.idAwayTeam
        loadDataLocal(idEvent = event.idEvent)
    }

    private fun loadDataLocal(idEvent: String) {
        database.use {
            select(EntityEvent.TABLE_EVENT)
                    .whereArgs("(${EntityEvent.ID_EVENT} = {idEvent})",
                            "idEvent" to idEvent).exec {
                        this@DetailMatchActivity.count = count
                        setupData()
                    }
        }
    }

    private fun setupData() {
        presenter.onLoadImageClub(idHomeTeam = idHomeTeam, idAwayTeam = idAwayTeam)

        // load data score
        text_view_home_score_activity_detail_match.text = event.intHomeScore
        text_view_away_score_activity_detail_match.text = event.intAwayScore

        // load data club name
        text_view_home_club_name_activity_detail_match.text = event.strHomeTeam
        text_view_away_club_name_activity_detail_match.text = event.strAwayTeam

        // load data formation
        text_view_home_formation_activity_detail_match.text = event.strHomeFormation
        text_view_away_formation_activity_detail_match.text = event.strAwayFormation

        // load data goals
        text_view_home_goal_activity_detail_match.text = event.strHomeGoalDetails?.replace(";", "\n")
        text_view_away_goal_activity_detail_match.text = event.strAwayGoalDetails?.replace(";", "\n")

        // load data shots
        text_view_home_shot_activity_detail_match.text = event.intHomeShots
        text_view_away_shot_activity_detail_match.text = event.intAwayShots

        // load data goalkeeper
        text_view_home_goalkeeper_activity_detail_match.text = event.strHomeLineupGoalkeeper?.replace(";", "\n")
        text_view_away_goalkeeper_activity_detail_match.text = event.strAwayLineupGoalkeeper?.replace(";", "\n")

        // load data defense
        text_view_home_defense_activity_detail_match.text = event.strHomeLineupDefense?.replace(";", "\n")
        text_view_away_defense_activity_detail_match.text = event.strAwayLineupDefense?.replace(";", "\n")

        // load data midfield
        text_view_home_midfield_activity_detail_match.text = event.strHomeLineupMidfield?.replace(";", "\n")
        text_view_away_midfield_activity_detail_match.text = event.strAwayLineupMidfield?.replace(";", "\n")

        // load data forward
        text_view_home_forward_activity_detail_match.text = event.strHomeLineupForward?.replace(";", "\n")
        text_view_away_forward_activity_detail_match.text = event.strAwayLineupForward?.replace(";", "\n")

        // load data substitute
        text_view_home_substitute_activity_detail_match.text = event.strHomeLineupSubstitutes?.replace(";", "\n")
        text_view_away_substitute_activity_detail_match.text = event.strAwayLineupSubstitutes?.replace(";", "\n")
        hideLoading()
    }

    private fun initListeners() {
        swipe_refresh_layout_activity_detail_match.setOnRefreshListener {
            setupData()
        }
    }

    private fun showLoading() {
        swipe_refresh_layout_activity_detail_match.isRefreshing = true
        linear_layout_container_content_activity_detail_match.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        swipe_refresh_layout_activity_detail_match.isRefreshing = false
        linear_layout_container_content_activity_detail_match.visibility = View.VISIBLE
    }

    private fun initPresenter() {
        gson = Gson()
        apiRepository = ApiRepository()
        presenter = DetailMatchPresenter(view = this, apiRepository = apiRepository, gson = gson)
    }

    override fun loadImageClub(homeLogo: List<Team>, awayLogo: List<Team>) {
        Glide.with(this)
                .load(homeLogo[0].strTeamBadge)
                .into(image_view_home_activity_detail_match)
        Glide.with(this)
                .load(awayLogo[0].strTeamBadge)
                .into(image_view_away_activity_detail_match)
    }

    override fun loadImageClubFailed(message: String) {
        toast("Failed load image club")
    }

}
