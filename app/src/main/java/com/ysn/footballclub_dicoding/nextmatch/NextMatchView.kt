/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:18 AM
 */

package com.ysn.footballclub_dicoding.nextmatch

import com.ysn.footballclub_dicoding.model.Event

interface NextMatchView {

    fun loadData(events: List<Event>)

    fun loadDataFailed(message: String)

    fun onClickItemNextMatch(event: Event)

}
