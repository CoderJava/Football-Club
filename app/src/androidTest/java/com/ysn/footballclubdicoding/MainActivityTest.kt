/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
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
        delaySecond()
        onView(withId(R.id.recycler_view_match_fragment_previous_match))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_match_fragment_previous_match))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))
        onView(withId(R.id.recycler_view_match_fragment_previous_match))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))
        delaySecond()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match))
                .perform(click())
        delaySecond()
    }

    @Test
    fun testRecyclerViewNextMatch() {
        delaySecond()
        onView(withId(R.id.tab_layout_matches_fragment_match))
                .check(matches(isDisplayed()))
        onView(withText("Next Match"))
                .perform(click())
        onView(withId(R.id.recycler_view_match_fragment_next_match))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))
        onView(withId(R.id.recycler_view_match_fragment_next_match))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, click()))
        delaySecond()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match))
                .perform(click())
        delaySecond()
    }

    @Test
    fun testSearchingMatch() {
        delaySecond()
        onView(withId(R.id.menu_item_search_menu_matches))
                .check(matches(isDisplayed()))
        onView(withId(R.id.menu_item_search_menu_matches))
                .perform(click())
        onView(withId(R.id.edit_text_keyword_activity_search_matches))
                .perform(clearText(), typeText("Arsenal"))
        onView(withId(R.id.edit_text_keyword_activity_search_matches))
                .perform(pressImeActionButton())
        delaySecond()
        onView(withId(R.id.recycler_view_search_activity_search_matches))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_search_activity_search_matches))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        delaySecond(10)
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match))
                .perform(click())
        delaySecond()
    }

    @Test
    fun testRecyclerViewTeams() {
        delaySecond()
        onView(withId(R.id.navigation_teams))
                .perform(click())
        delaySecond()
        onView(withId(R.id.recycler_view_team_fragment_teams))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_team_fragment_teams))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        delaySecond()
        onView(withText("Player"))
                .perform(click())
        onView(withId(R.id.recycler_view_player_detail_team))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.menu_item_add_favorite_menu_detail_team))
                .perform(click())
        delaySecond()
    }

    @Test
    fun testSearchingTeam() {
        delaySecond()
        onView(withId(R.id.navigation_teams))
                .perform(click())
        delaySecond()
        onView(withId(R.id.menu_item_search_menu_matches))
                .check(matches(isDisplayed()))
        onView(withId(R.id.menu_item_search_menu_matches))
                .perform(click())
        onView(withId(R.id.edit_text_keyword_activity_search_teams))
                .perform(clearText(), typeText("Arsenal"))
        onView(withId(R.id.edit_text_keyword_activity_search_teams))
                .perform(pressImeActionButton())
        delaySecond(5)
        onView(withId(R.id.recycler_view_search_activity_search_teams))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view_search_activity_search_teams))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        delaySecond()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_team))
                .perform(click())
        delaySecond()
    }

    @Test
    fun testRecyclerViewFavorite() {
        delaySecond()
        onView(withId(R.id.navigation_favorites))
                .perform(click())
        delaySecond()
        onView(withText("Favorite Team"))
                .perform(click())
        delaySecond()
    }

    private fun delaySecond(second: Long = 3) {
        Thread.sleep(1000 * second)
    }

}