/*
 * Created by YSN Studio on 4/24/18 12:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/23/18 11:43 PM
 */

package com.ysn.footballclub_dicoding

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewPreviousMatch() {
        delay3Second()
        onView(withId(R.id.recycler_view_previous_match_fragment))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_previous_match_fragment))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))
        onView(withId(R.id.recycler_view_previous_match_fragment))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))
        delay3Second()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match))
                .perform(click())
        delay3Second()
    }

    @Test
    fun testRecyclerViewNextMatch() {
        delay3Second()
        onView(withId(R.id.bottom_navigation_view_activity_main))
                .check(matches(isDisplayed()))
        onView(withId(R.id.navigation_teams))
                .perform(click())
        delay3Second()
        onView(withId(R.id.recycler_view_next_match_fragment))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_next_match_fragment))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(14, click()))
        delay3Second()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match))
                .perform(click())
        delay3Second()
    }

    @Test
    fun testRecyclerFavoriteMatch() {
        delay3Second()
        onView(withId(R.id.bottom_navigation_view_activity_main))
                .check(matches(isDisplayed()))
        onView(withId(R.id.navigation_favorites))
                .perform(click())
        delay3Second()
        onView(withId(R.id.recycler_view_favorite_match_fragment))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_favorite_match_fragment))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay3Second()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match))
                .perform(click())
        delay3Second()
    }

    private fun delay3Second() {
        Thread.sleep(1000 * 3)
    }

}