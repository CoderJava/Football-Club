/*
 * Created by YSN Studio on 4/24/18 12:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/23/18 11:51 PM
 */

package com.ysn.footballclub_dicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ysn.footballclub_dicoding.favorites.FavoritesFragment
import com.ysn.footballclub_dicoding.matches.MatchesFragment
import com.ysn.footballclub_dicoding.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_activity_main, MatchesFragment())
                .commit()
        bottom_navigation_view_activity_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_matches -> {
                    val matchesFragment = MatchesFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, matchesFragment)
                            .commit()
                    true
                }
                R.id.navigation_teams -> {
                    val teamsFragment = TeamsFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, teamsFragment)
                            .commit()
                    true
                }
                R.id.navigation_favorites -> {
                    val favoritesFragment = FavoritesFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, favoritesFragment)
                            .commit()
                    true
                }
                else -> {
                    /* nothing to do in here */
                    true
                }
            }
        }
    }

}
