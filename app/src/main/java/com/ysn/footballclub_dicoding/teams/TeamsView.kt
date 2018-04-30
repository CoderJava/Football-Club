/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 10:07 PM
 */

package com.ysn.footballclub_dicoding.teams

import com.ysn.footballclub_dicoding.model.teams.AllTeamItem

interface TeamsView {

    fun loadDataAllTeams(allTeams: List<AllTeamItem>)

}
