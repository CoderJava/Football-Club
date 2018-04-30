/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.detailmatch

import com.ysn.footballclub_dicoding.model.matches.TeamMatches

interface DetailMatchView {

    fun loadImageClub(homeLogos: ArrayList<TeamMatches>, awayLogos: ArrayList<TeamMatches>)

}
