/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ysn.footballclubdicoding.favorites.FavoritesFragment
import com.ysn.footballclubdicoding.matches.MatchesFragment
import com.ysn.footballclubdicoding.matches.activitiy.searchmatch.SearchMatchesActivity
import com.ysn.footballclubdicoding.teams.TeamsFragment
import com.ysn.footballclubdicoding.teams.activity.searchteam.SearchTeamsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity(), AnkoLogger {

    private val TAG = javaClass.simpleName

    private lateinit var menu: Menu
    private var indexFragmentSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indexFragmentSelected = 0
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_activity_main, MatchesFragment())
                .commit()
        bottom_navigation_view_activity_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_matches -> {
                    indexFragmentSelected = 0
                    val matchesFragment = MatchesFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, matchesFragment)
                            .commit()
                    true
                }
                R.id.navigation_teams -> {
                    indexFragmentSelected = 1
                    val teamsFragment = TeamsFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, teamsFragment)
                            .commit()
                    true
                }
                R.id.navigation_favorites -> {
                    indexFragmentSelected = 2
                    val favoritesFragment = FavoritesFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, favoritesFragment)
                            .commit()
                    true
                }
                else -> {
                    /* nothing to do in here */
                    false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = when (indexFragmentSelected) {
        0 -> {
            menuInflater.inflate(R.menu.menu_matches, menu)
            this.menu = menu
            true
        }
        else -> {
            super.onCreateOptionsMenu(menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_item_search_menu_matches -> {
            when (indexFragmentSelected) {
                0 -> {
                    val intentSearchMatches = intentFor<SearchMatchesActivity>()
                    startActivity(intentSearchMatches)
                }
                1 -> {
                    val intentSearchTeams = intentFor<SearchTeamsActivity>()
                    startActivity(intentSearchTeams)
                }
                2 -> {
                    /* nothing to do in here */
                }
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}
