/*
 * Created by YSN Studio on 5/2/18 9:57 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 10:12 PM
 */

package com.ysn.footballclub_dicoding.model.matches

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class LeagueMatches(
        @SerializedName("events") val eventMatches: List<EventMatches>
)

data class EventMatches(
        @SerializedName("idEvent") var idEvent: String = "",
        @SerializedName("idSoccerXML") val idSoccerXML: String = "",
        @SerializedName("strEvent") val strEvent: String = "",
        @SerializedName("strFilename") val strFilename: String = "",
        @SerializedName("strSport") val strSport: String = "",
        @SerializedName("idLeague") val idLeague: String = "",
        @SerializedName("strLeague") val strLeague: String = "",
        @SerializedName("strSeason") val strSeason: String = "",
        @SerializedName("strDescriptionEN") val strDescriptionEN: Any = "",
        @SerializedName("strHomeTeam") val strHomeTeam: String? = "",
        @SerializedName("strAwayTeam") val strAwayTeam: String? = "",
        @SerializedName("intHomeScore") val intHomeScore: String?,
        @SerializedName("intRound") val intRound: String = "",
        @SerializedName("intAwayScore") val intAwayScore: String? = "",
        @SerializedName("intSpectators") val intSpectators: Any = "",
        @SerializedName("strHomeGoalDetails") val strHomeGoalDetails: String?,
        @SerializedName("strHomeRedCards") val strHomeRedCards: Any = "",
        @SerializedName("strHomeYellowCards") val strHomeYellowCards: Any = "",
        @SerializedName("strHomeLineupGoalkeeper") val strHomeLineupGoalkeeper: String? = "",
        @SerializedName("strHomeLineupDefense") val strHomeLineupDefense: String? = "",
        @SerializedName("strHomeLineupMidfield") val strHomeLineupMidfield: String? = "",
        @SerializedName("strHomeLineupForward") val strHomeLineupForward: String? = "",
        @SerializedName("strHomeLineupSubstitutes") val strHomeLineupSubstitutes: String? = "",
        @SerializedName("strHomeFormation") val strHomeFormation: String? = "",
        @SerializedName("strAwayRedCards") val strAwayRedCards: Any = "",
        @SerializedName("strAwayYellowCards") val strAwayYellowCards: Any = "",
        @SerializedName("strAwayGoalDetails") val strAwayGoalDetails: String? = "",
        @SerializedName("strAwayLineupGoalkeeper") val strAwayLineupGoalkeeper: String? = "",
        @SerializedName("strAwayLineupDefense") val strAwayLineupDefense: String? = "",
        @SerializedName("strAwayLineupMidfield") val strAwayLineupMidfield: String? = "",
        @SerializedName("strAwayLineupForward") val strAwayLineupForward: String? = "",
        @SerializedName("strAwayLineupSubstitutes") val strAwayLineupSubstitutes: String? = "",
        @SerializedName("strAwayFormation") val strAwayFormation: String? = "",
        @SerializedName("intHomeShots") val intHomeShots: String? = "",
        @SerializedName("intAwayShots") val intAwayShots: String? = "",
        @SerializedName("dateEvent") val dateEvent: String? = "",
        @SerializedName("strDate") val strDate: String = "",
        @SerializedName("strTime") val strTime: String = "",
        @SerializedName("strTVStation") val strTVStation: Any = "",
        @SerializedName("idHomeTeam") val idHomeTeam: String = "",
        @SerializedName("idAwayTeam") val idAwayTeam: String = "",
        @SerializedName("strResult") val strResult: Any = "",
        @SerializedName("strCircuit") val strCircuit: Any = "",
        @SerializedName("strCountry") val strCountry: Any = "",
        @SerializedName("strCity") val strCity: Any = "",
        @SerializedName("strPoster") val strPoster: Any = "",
        @SerializedName("strFanart") val strFanart: Any = "",
        @SerializedName("strThumb") val strThumb: Any = "",
        @SerializedName("strBanner") val strBanner: Any = "",
        @SerializedName("strMap") val strMap: Any = "",
        @SerializedName("strLocked") val strLocked: String = ""
): Serializable