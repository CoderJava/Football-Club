/*
 * Created by YSN Studio on 5/1/18 11:50 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 11:49 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.detailteam

import android.content.Entity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.db.EntityTeam
import com.ysn.footballclub_dicoding.db.database
import com.ysn.footballclub_dicoding.model.teams.AllTeamItem
import com.ysn.footballclub_dicoding.teams.activity.detailteam.adapter.ViewPagerAdapterDetailTeams
import com.ysn.footballclub_dicoding.teams.fragment.overviewteam.OverviewDetailTeamFragment
import com.ysn.footballclub_dicoding.teams.fragment.playerteam.PlayerDetailTeamFragment
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import org.jetbrains.anko.toolbar

class DetailTeamActivity : AppCompatActivity(), AnkoLogger {

    private val TAG = javaClass.simpleName
    private lateinit var viewPagerAdapterDetailTeams: ViewPagerAdapterDetailTeams
    private lateinit var allTeamItem: AllTeamItem
    private lateinit var menu: Menu
    private var count: Int = 0
    private var isAlreadyDataInLocal = true

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
        this.menu = menu
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_team).let {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_item_add_favorite_menu_detail_team -> {
            if (isAlreadyDataInLocal) {
                deleteFavoriteTeam()
            } else {
                addFavoriteTeam()
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun addFavoriteTeam() {
        database.use {
            insert(EntityTeam.TABLE_TEAM,
                    EntityTeam.ID_TEAM to allTeamItem.idTeam,
                    EntityTeam.STR_TEAM to allTeamItem.strTeam,
                    EntityTeam.STR_ALTERNATE to allTeamItem.strAlternate,
                    EntityTeam.INT_FORMED_YEAR to allTeamItem.intFormedYear,
                    EntityTeam.STR_STADIUM to allTeamItem.strStadium,
                    EntityTeam.STR_DESCRIPTION_EN to allTeamItem.strDescriptionEN,
                    EntityTeam.STR_COUNTRY to allTeamItem.strCountry,
                    EntityTeam.STR_TEAM_BADGE to allTeamItem.strTeamBadge
            )
            isAlreadyDataInLocal = true
            menu.findItem(R.id.menu_item_add_favorite_menu_detail_team)
                    .setIcon(R.drawable.ic_star_white_24dp)
            toast("Team has been added to Favorite")
        }
    }

    private fun deleteFavoriteTeam() {
        database.use {
            delete(EntityTeam.TABLE_TEAM,
                    "(${EntityTeam.ID_TEAM} = {idTeam})",
                    "idTeam" to allTeamItem.idTeam)
        }
        isAlreadyDataInLocal = false
        menu.findItem(R.id.menu_item_add_favorite_menu_detail_team)
                .setIcon(R.drawable.ic_star_border_white_24dp)
        toast("Team has been removed from Favorite")
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
        loadDataLocal()
    }

    private fun loadDataLocal() {
        database.use {
            select(EntityTeam.TABLE_TEAM)
                    .whereArgs("(${EntityTeam.ID_TEAM} = {idTeam})",
                            "idTeam" to allTeamItem.idTeam).exec {
                        this@DetailTeamActivity.count = count
                    }
        }
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
