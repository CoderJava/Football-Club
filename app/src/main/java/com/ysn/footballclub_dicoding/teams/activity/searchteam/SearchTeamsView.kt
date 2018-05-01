/*
 * Created by YSN Studio on 5/1/18 5:34 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 5:30 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.searchteam

import com.ysn.footballclub_dicoding.model.teams.AllTeamItem

interface SearchTeamsView {

    fun searchingTeamByClubName(allTeams: List<AllTeamItem>)

}
