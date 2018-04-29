/*
 * Created by YSN Studio on 4/29/18 10:50 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/29/18 10:48 PM
 */

package com.ysn.footballclub_dicoding.matches.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapterMatches constructor(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

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