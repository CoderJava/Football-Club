/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 2:13 PM
 */

package com.ysn.footballclub_dicoding.detailmatch

interface DetailMatchView {

    fun loadImageClub(homeLogo: String, awayLogo: String)

    fun loadImageClubFailed(message: String)

}
