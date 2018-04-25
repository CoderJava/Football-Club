/*
 * Created by YSN Studio on 4/25/18 3:14 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/25/18 2:42 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.detailmatch

import com.ysn.footballclub_dicoding.model.Team

interface DetailMatchView {

    fun loadImageClub(homeLogo: ArrayList<Team>, awayLogo: ArrayList<Team>)

}
