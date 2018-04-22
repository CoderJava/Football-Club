/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:09 AM
 */

package com.ysn.footballclub_dicoding.previousmatch

import com.ysn.footballclub_dicoding.model.Event

interface PreviousMatchView {


    fun loadData(events: List<Event>)

    fun loadDataFailed(message: String)

    fun onClickItemPreviousMatch(event: Event)
}