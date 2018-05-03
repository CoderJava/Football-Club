/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:34 PM
 */

package com.ysn.footballclubdicoding.db

import java.io.Serializable

data class EntityTeam constructor(val idTeam: String,
                                  val strTeam: String?,
                                  val strAlternate: String?,
                                  val intFormedYear: String?,
                                  val strStadium: String?,
                                  val strDescriptionEN: String?,
                                  val strCountry: String?,
                                  val strTeamBadge: String?) : Serializable {

    companion object {
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID_TEAM = "ID_TEAM"
        const val STR_TEAM = "STR_TEAM"
        const val STR_ALTERNATE = "STR_ALTERNATE"
        const val INT_FORMED_YEAR = "INT_FORMED_YEAR"
        const val STR_STADIUM = "STR_STADIUM"
        const val STR_DESCRIPTION_EN = "STR_DESCRIPTION_EN"
        const val STR_COUNTRY = "STR_COUNTRY"
        const val STR_TEAM_BADGE = "STR_TEAM_BADGE"
    }

}
