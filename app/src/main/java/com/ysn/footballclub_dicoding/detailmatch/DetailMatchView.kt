/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 10:56 AM
 */

package com.ysn.footballclub_dicoding.detailmatch

import com.ysn.footballclub_dicoding.model.Team

interface DetailMatchView {

    fun loadImageClub(homeLogo: List<Team>, awayLogo: List<Team>)

    fun loadImageClubFailed(message: String)

}
