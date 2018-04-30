/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch

import com.ysn.footballclub_dicoding.model.matches.EventMatches

interface NextMatchView {

    fun loadDataEventsNextLeague(eventMatches: List<EventMatches>)

}
