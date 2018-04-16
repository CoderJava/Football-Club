/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 1:27 PM
 */

package com.ysn.footballclub_dicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ysn.footballclub_dicoding.favoritematch.FavoriteMatchFragment
import com.ysn.footballclub_dicoding.nextmatch.NextMatchFragment
import com.ysn.footballclub_dicoding.previousmatch.PreviousMatchFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_activity_main, PreviousMatchFragment())
                .commit()
        bottom_navigation_view_activity_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_previous_match -> {
                    val previousMatchFragment = PreviousMatchFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, previousMatchFragment)
                            .commit()
                    true
                }
                R.id.navigation_next_match -> {
                    val nextMatchFragment = NextMatchFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, nextMatchFragment)
                            .commit()
                    true
                }
                R.id.navigation_favorite_match -> {
                    val favoriteMatchFragment = FavoriteMatchFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, favoriteMatchFragment)
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
