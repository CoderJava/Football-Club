/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 11:25 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.detailteam.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapterDetailTeams constructor(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    private val TAG = javaClass.simpleName
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = mFragmentTitleList[position]

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}
