/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 2:19 PM
 */

package com.ysn.footballclub_dicoding.detailmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.Event
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var event: Event
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        initPresenter()
        doLoadData()
        initListeners()
    }

    private fun doLoadData() {
        showLoading()
        val bundle = intent.extras
        event = bundle?.getSerializable("event") as Event
        idHomeTeam = event.idHomeTeam
        idAwayTeam = event.idAwayTeam
        setupData()
    }

    private fun setupData() {
        presenter.onLoadImageClub(idHomeTeam = idHomeTeam, idAwayTeam = idAwayTeam)

        // load data score
        if (event.intHomeScore != null) {
            text_view_home_score_activity_detail_match.text = event.intHomeScore
        }
        if (event.intAwayScore != null) {
            text_view_away_score_activity_detail_match.text = event.intAwayScore
        }

        // load data club name
        if (event.strHomeTeam != null) {
            text_view_home_club_name_activity_detail_match.text = event.strHomeTeam
        }
        if (event.strAwayTeam != null) {
            text_view_away_club_name_activity_detail_match.text = event.strAwayTeam
        }

        // load data formation
        if (event.strHomeFormation != null) {
            text_view_home_formation_activity_detail_match.text = event.strHomeFormation
        }
        if (event.strAwayFormation != null) {
            text_view_away_formation_activity_detail_match.text = event.strAwayFormation
        }

        // load data goals
        if (event.strHomeGoalDetails != null) {
            text_view_home_goal_activity_detail_match.text = event.strHomeGoalDetails.replace(";", "\n")
        }
        if (event.strAwayGoalDetails != null) {
            text_view_away_goal_activity_detail_match.text = event.strAwayGoalDetails.replace(";", "\n")
        }

        // load data shots
        if (event.intHomeShots != null) {
            text_view_home_shot_activity_detail_match.text = event.intHomeShots
        }
        if (event.intAwayShots != null) {
            text_view_away_shot_activity_detail_match.text = event.intAwayShots
        }

        // load data goalkeeper
        if (event.strHomeLineupGoalkeeper != null) {
            text_view_home_goalkeeper_activity_detail_match.text = event.strHomeLineupGoalkeeper.replace(";", "\n")
        }
        if (event.strAwayLineupGoalkeeper != null) {
            text_view_away_goalkeeper_activity_detail_match.text = event.strAwayLineupGoalkeeper.replace(";", "\n")
        }

        // load data defense
        if (event.strHomeLineupDefense != null) {
            text_view_home_defense_activity_detail_match.text = event.strHomeLineupDefense.replace(";", "\n")
        }
        if (event.strAwayLineupDefense != null) {
            text_view_away_defense_activity_detail_match.text = event.strAwayLineupDefense.replace(";", "\n")
        }

        // load data midfield
        if (event.strHomeLineupMidfield != null) {
            text_view_home_midfield_activity_detail_match.text = event.strHomeLineupMidfield.replace(";", "\n")
        }
        if (event.strAwayLineupMidfield != null) {
            text_view_away_midfield_activity_detail_match.text = event.strAwayLineupMidfield.replace(";", "\n")
        }

        // load data forward
        if (event.strHomeLineupForward != null) {
            text_view_home_forward_activity_detail_match.text = event.strHomeLineupForward.replace(";", "\n")
        }
        if (event.strAwayLineupForward != null) {
            text_view_away_forward_activity_detail_match.text = event.strAwayLineupForward.replace(";", "\n")
        }

        // load data substitute
        if (event.strHomeLineupSubstitutes != null) {
            text_view_home_substitute_activity_detail_match.text = event.strHomeLineupSubstitutes.replace(";", "\n")
        }
        if (event.strAwayLineupSubstitutes != null) {
            text_view_away_substitute_activity_detail_match.text = event.strAwayLineupSubstitutes.replace(";", "\n")
        }
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
}
