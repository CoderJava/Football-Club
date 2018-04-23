/*
 * Created by YSN Studio on 4/24/18 1:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/24/18 1:48 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.previousmatch

import com.ysn.footballclub_dicoding.model.Event

interface PreviousMatchView {

    fun loadDataEventsPastLeague(events: List<Event>)
}