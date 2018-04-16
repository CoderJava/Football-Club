/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 12:45 PM
 */

package com.ysn.footballclub_dicoding.favoritematch

import com.ysn.footballclub_dicoding.db.EntityEvent
import com.ysn.footballclub_dicoding.favoritematch.adapter.AdapterMatchFavorite

interface FavoriteMatchView {

    fun loadData(adapterMatch: AdapterMatchFavorite)

    fun loadDataFailed(localizedMessage: String)

    fun onClickItemFavoriteMatch(event: EntityEvent)

    fun refreshData()

    fun refreshDataFailed(localizedMessage: String)
}