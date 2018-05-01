/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 4:33 AM
 */

package com.ysn.footballclub_dicoding.teams.fragment.playerteam

import com.ysn.footballclub_dicoding.model.teams.Player

interface PlayerDetailTeamView {

    fun loadDataAllPlayers(allPlayers: List<Player>)

}
