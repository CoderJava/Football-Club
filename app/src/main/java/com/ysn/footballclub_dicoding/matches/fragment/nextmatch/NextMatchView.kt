/*
 * Created by YSN Studio on 4/26/18 3:27 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 3:02 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch

import com.ysn.footballclub_dicoding.model.Event

interface NextMatchView {

    fun loadDataEventsNextLeague(events: List<Event>)

}
