/*
 * Created by YSN Studio on 5/1/18 5:34 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 4:34 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.detailteam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.teams.AllTeamItem
import com.ysn.footballclub_dicoding.teams.activity.detailteam.adapter.ViewPagerAdapterDetailTeams
import com.ysn.footballclub_dicoding.teams.fragment.overviewteam.OverviewDetailTeamFragment
import com.ysn.footballclub_dicoding.teams.fragment.playerteam.PlayerDetailTeamFragment
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toolbar

class DetailTeamActivity : AppCompatActivity(), AnkoLogger {

    private val TAG = javaClass.simpleName
    private lateinit var viewPagerAdapterDetailTeams: ViewPagerAdapterDetailTeams
    private lateinit var allTeamItem: AllTeamItem
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        initToolbar()
        doLoadData()
        setupTabLayout()
        setupViewPager()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_activity_detail_team)
        supportActionBar?.title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_team, menu)
        // TODO: do something in here (pending)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_item_add_favorite_menu_detail_team -> {
            // TODO: do something in here (pending)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun doLoadData() {
        val bundle = intent.extras
        allTeamItem = bundle?.getSerializable("allTeamItem") as AllTeamItem
        Glide.with(this)
                .load(allTeamItem.strTeamBadge)
                .into(image_view_logo_club_activity_detail_team)
        text_view_club_name_activity_detail_team.text = allTeamItem.strTeam
        text_view_formed_year_club_activity_detail_team.text = allTeamItem.intFormedYear
        text_view_alternate_name_club_activity_detail_team.text = allTeamItem.strAlternate
    }

    private fun setupTabLayout() {
        tab_layout_team_activity_detail_team.setupWithViewPager(view_pager_team_activity_detail_team)
    }

    private fun setupViewPager() {
        viewPagerAdapterDetailTeams = ViewPagerAdapterDetailTeams(supportFragmentManager)
        val bundleOverviewDetailTeam = Bundle()
        bundleOverviewDetailTeam.putString("overview", allTeamItem.strDescriptionEN)
        val overviewDetailTeamFragment = OverviewDetailTeamFragment()
        overviewDetailTeamFragment.arguments = bundleOverviewDetailTeam
        viewPagerAdapterDetailTeams.addFragment(overviewDetailTeamFragment, getString(R.string.title_overview))

        val bundlePlayerDetailTeam = Bundle()
        bundlePlayerDetailTeam.putString("idTeam", allTeamItem.idTeam)
        val playerDetailTeamFragment = PlayerDetailTeamFragment()
        playerDetailTeamFragment.arguments = bundlePlayerDetailTeam
        viewPagerAdapterDetailTeams.addFragment(playerDetailTeamFragment, getString(R.string.title_player))
        view_pager_team_activity_detail_team.adapter = viewPagerAdapterDetailTeams
    }
}
