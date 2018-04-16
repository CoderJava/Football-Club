/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 1:08 PM
 */

package com.ysn.footballclub_dicoding.db

import java.io.Serializable

data class EntityEvent constructor(val idEvent: String?,
                                   val dateEvent: String?,
                                   val idHomeTeam: String?,
                                   val idAwayTeam: String?,
                                   val intHomeScore: String?,
                                   val intAwayScore: String?,
                                   val strHomeTeam: String?,
                                   val strAwayTeam: String?,
                                   val strHomeFormation: String?,
                                   val strAwayFormation: String?,
                                   val strHomeGoalDetails: String?,
                                   val strAwayGoalDetails: String?,
                                   val intHomeShots: String?,
                                   val intAwayShots: String?,
                                   val strHomeLineupGoalKeeper: String?,
                                   val strAwayLineupGoalKeeper: String?,
                                   val strHomeLineupDefense: String?,
                                   val strAwayLineupDefense: String?,
                                   val strHomeLineupMidfield: String?,
                                   val strAwayLineupMidfield: String?,
                                   val strHomeLineupForward: String?,
                                   val strAwayLineupForward: String?,
                                   val strHomeLineupSubstitutes: String?,
                                   val strAwayLineupSubstitutes: String?): Serializable {

    companion object {
        const val TABLE_EVENT = "TABLE_EVENT"
        const val ID_EVENT = "ID_EVENT"
        const val DATE_EVENT = "DATE_EVENT"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val INT_HOME_SCORE = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE = "INT_AWAY_SCORE"
        const val STR_HOME_TEAM = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM = "STR_AWAY_TEAM"
        const val STR_HOME_FORMATION = "STR_HOME_FORMATION"
        const val STR_AWAY_FORMATION = "STR_AWAY_FORMATION"
        const val STR_HOME_GOAL_DETAILS = "STR_HOME_GOAL_DETAILS"
        const val STR_AWAY_GOAL_DETAILS = "STR_AWAY_GOAL_DETAILS"
        const val INT_HOME_SHOTS = "INT_HOME_SHOTS"
        const val INT_AWAY_SHOTS = "INT_AWAY_SHOTS"
        const val STR_HOME_LINEUP_GOAL_KEEPER = "STR_HOME_LINEUP_GOAL_KEEPER"
        const val STR_AWAY_LINEUP_GOAL_KEEPER = "STR_AWAY_LINEUP_GOAL_KEEPER"
        const val STR_HOME_LINEUP_DEFENSE = "STR_HOME_LINEUP_DEFENSE"
        const val STR_AWAY_LINEUP_DEFENSE = "STR_AWAY_LINEUP_DEFENSE"
        const val STR_HOME_LINEUP_MIDFIELD = "STR_HOME_LINEUP_MIDFIELD"
        const val STR_AWAY_LINEUP_MIDFIELD = "STR_AWAY_LINEUP_MIDFIELD"
        const val STR_HOME_LINEUP_FORWARD = "STR_HOME_LINEUP_FORWARD"
        const val STR_AWAY_LINEUP_FORWARD = "STR_AWAY_LINEUP_FORWARD"
        const val STR_HOME_LINEUP_SUBSTITUTES = "STR_HOME_LINEUP_SUBSTITUTES"
        const val STR_AWAY_LINEUP_SUBSTITUTES = "STR_AWAY_LINEUP_SUBSTITUTES"
    }
}