/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 11:37 AM
 */

package com.ysn.footballclub_dicoding.detailmatch

interface DetailMatchView {

    fun loadImageClub(homeLogo: String, awayLogo: String)

    fun loadImageClubFailed(message: String)

    fun addFavoriteMatch()

    fun addFavoriteMatchFailed(localizedMessage: String)

    fun loadData(count: Int)

    fun loadDataFailed(localizedMessage: String)

    fun deleteFavoriteMatch()

    fun deleteFavoriteMatchFailed(localizedMessage: String)

}
