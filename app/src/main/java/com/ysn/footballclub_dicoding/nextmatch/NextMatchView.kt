/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 6:02 PM
 */

package com.ysn.footballclub_dicoding.nextmatch

import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event

interface NextMatchView {

    fun loadData(adapterMatch: AdapterMatch)

    fun loadDataFailed(message: String)

    fun refreshData()

    fun refreshDataFailed(message: String)

    fun onClickItemNextMatch(event: Event)

}
