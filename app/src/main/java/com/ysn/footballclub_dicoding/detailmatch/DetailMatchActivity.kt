/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 1:09 PM
 */

package com.ysn.footballclub_dicoding.detailmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.db.DatabaseOpenHelper
import com.ysn.footballclub_dicoding.db.EntityEvent
import com.ysn.footballclub_dicoding.model.Event
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailMatchView, AnkoLogger {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var database: DatabaseOpenHelper
    private lateinit var event: Event
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var menu: Menu
    private var count: Int = 0
    private var isAlreadyDataInLocal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        initPresenter()
        initDatabase()
        initListeners()
        doLoadData()
    }

    private fun initDatabase() {
        database = DatabaseOpenHelper.getInstance(this)
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
                    presenter.onDeleteFavoriteMatch(database = database, event = event)
                } else {
                    presenter.onAddFavoriteMatch(database = database, event = event)
                }
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
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
        presenter.onLoadData(database = database, idEvent = event.idEvent)
    }

    override fun loadData(count: Int) {
        this.count = count
        info { "count: $count" }
        setupData()
    }

    override fun loadDataFailed(localizedMessage: String) {
        longToast(localizedMessage)
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
        presenter = DetailMatchPresenter(this)
    }

    override fun loadImageClub(homeLogo: String, awayLogo: String) {
        Glide.with(this)
                .load(homeLogo)
                .into(image_view_home_activity_detail_match)
        Glide.with(this)
                .load(awayLogo)
                .into(image_view_away_activity_detail_match)
    }

    override fun loadImageClubFailed(message: String) {
        toast("Failed load image club")
    }

    override fun addFavoriteMatch() {
        toast("Match has been added to Favorite")
        isAlreadyDataInLocal = true
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_match)
                .setIcon(R.drawable.ic_star_white_24dp)
    }

    override fun addFavoriteMatchFailed(localizedMessage: String) {
        longToast(localizedMessage)
    }

    override fun deleteFavoriteMatch() {
        toast("Match has been removed from Favorite")
        isAlreadyDataInLocal = false
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_match)
                .setIcon(R.drawable.ic_star_border_white_24dp)
    }

    override fun deleteFavoriteMatchFailed(localizedMessage: String) {
        longToast(localizedMessage)
    }
}
