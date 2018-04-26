/*
 * Created by YSN Studio on 4/26/18 11:09 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 10:36 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.searchmatch

import com.ysn.footballclub_dicoding.model.EventSearchLeague

interface SearchMatchesView {

    fun searchEventByClubName(events: List<EventSearchLeague>)

}
