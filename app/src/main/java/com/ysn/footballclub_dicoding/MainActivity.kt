/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 1:30 PM
 */

package com.ysn.footballclub_dicoding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ysn.footballclub_dicoding.nextmatch.NextMatchFragment
import com.ysn.footballclub_dicoding.previousmatch.PreviousMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_activity_main, PreviousMatchFragment())
                .commit()
        bottom_navigation_view_activity_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_previous_match -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, PreviousMatchFragment())
                            .commit()
                    true
                }
                R.id.navigation_next_match -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout_activity_main, NextMatchFragment())
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
