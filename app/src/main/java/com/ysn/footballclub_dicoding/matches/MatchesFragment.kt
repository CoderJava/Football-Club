/*
 * Created by YSN Studio on 4/24/18 12:18 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/24/18 12:10 AM
 */

package com.ysn.footballclub_dicoding.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.matches.adapter.ViewPagerAdapterMatches
import com.ysn.footballclub_dicoding.matches.fragment.nextmatch.NextMatchFragment
import com.ysn.footballclub_dicoding.matches.fragment.previousmatch.PreviousMatchFragment
import kotlinx.android.synthetic.main.fragment_matches.*


class MatchesFragment : Fragment() {

    private val TAG = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matches, container, false)
        return view
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
        val viewPagerAdapterMatches = ViewPagerAdapterMatches(fragmentManager)
        val previousMatchFragment = PreviousMatchFragment()
        val nextMatchFragment = NextMatchFragment()
        viewPagerAdapterMatches.addFragment(previousMatchFragment, getString(R.string.title_previous_match))
        viewPagerAdapterMatches.addFragment(nextMatchFragment, getString(R.string.title_next_match))
        view_pager_matches_fragment_match.adapter = viewPagerAdapterMatches
    }

}
