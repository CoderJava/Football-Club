/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.favorites.adapter.ViewPagerAdapterFavorites
import com.ysn.footballclubdicoding.favorites.fragment.matches.FavoriteMatchFragment
import com.ysn.footballclubdicoding.favorites.fragment.teams.FavoriteTeamFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.AnkoLogger

class FavoritesFragment : Fragment(), AnkoLogger {

    private val TAG = javaClass.simpleName
    private lateinit var viewPagerAdapterFavorites: ViewPagerAdapterFavorites

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        viewPagerAdapterFavorites = ViewPagerAdapterFavorites(fragmentManager)
        val favoriteMatchFragment = FavoriteMatchFragment()
        val favoriteTeamFragment = FavoriteTeamFragment()
        viewPagerAdapterFavorites.addFragment(favoriteMatchFragment, getString(R.string.title_favorite_match))
        viewPagerAdapterFavorites.addFragment(favoriteTeamFragment, getString(R.string.title_favorite_team))
        view_pager_fragment_favorites.adapter = viewPagerAdapterFavorites
    }

    private fun setupTabLayout() {
        tab_layout_fragment_favorites.setupWithViewPager(view_pager_fragment_favorites)
    }

}
