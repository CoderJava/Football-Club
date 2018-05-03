/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:59 PM
 */

package com.ysn.footballclubdicoding.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.matches.adapter.ViewPagerAdapterMatches
import com.ysn.footballclubdicoding.matches.fragment.nextmatch.NextMatchFragment
import com.ysn.footballclubdicoding.matches.fragment.previousmatch.PreviousMatchFragment
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.AnkoLogger


class MatchesFragment : Fragment(), AnkoLogger {

    private val TAG = javaClass.simpleName
    private lateinit var viewPagerAdapterMatches: ViewPagerAdapterMatches

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        tab_layout_matches_fragment_match.setupWithViewPager(view_pager_matches_fragment_match)
    }

    private fun setupViewPager() {
        viewPagerAdapterMatches = ViewPagerAdapterMatches(fragmentManager)
        val previousMatchFragment = PreviousMatchFragment()
        val nextMatchFragment = NextMatchFragment()
        viewPagerAdapterMatches.addFragment(previousMatchFragment, getString(R.string.title_previous_match))
        viewPagerAdapterMatches.addFragment(nextMatchFragment, getString(R.string.title_next_match))
        view_pager_matches_fragment_match.adapter = viewPagerAdapterMatches
    }

}
