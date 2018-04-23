/*
 * Created by YSN Studio on 4/24/18 12:18 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/24/18 12:03 AM
 */

package com.ysn.footballclub_dicoding.matches.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapterMatches constructor(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

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