/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 10:22 PM
 */

package com.ysn.footballclub_dicoding.model.teams
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AllTeams(
		@SerializedName("teams") val allTeamItems: List<AllTeamItem>
)

data class AllTeamItem(
		@SerializedName("idTeam") val idTeam: String = "",
		@SerializedName("idSoccerXML") val idSoccerXML: String = "",
		@SerializedName("intLoved") val intLoved: String = "",
		@SerializedName("strTeam") val strTeam: String = "",
		@SerializedName("strTeamShort") val strTeamShort: String = "",
		@SerializedName("strAlternate") val strAlternate: String = "",
		@SerializedName("intFormedYear") val intFormedYear: String = "",
		@SerializedName("strSport") val strSport: String = "",
		@SerializedName("strLeague") val strLeague: String = "",
		@SerializedName("idLeague") val idLeague: String = "",
		@SerializedName("strDivision") val strDivision: Any = "",
		@SerializedName("strManager") val strManager: String = "",
		@SerializedName("strStadium") val strStadium: String = "",
		@SerializedName("strKeywords") val strKeywords: String = "",
		@SerializedName("strRSS") val strRSS: String = "",
		@SerializedName("strStadiumThumb") val strStadiumThumb: String = "",
		@SerializedName("strStadiumDescription") val strStadiumDescription: String = "",
		@SerializedName("strStadiumLocation") val strStadiumLocation: String = "",
		@SerializedName("intStadiumCapacity") val intStadiumCapacity: String = "",
		@SerializedName("strWebsite") val strWebsite: String = "",
		@SerializedName("strFacebook") val strFacebook: String = "",
		@SerializedName("strTwitter") val strTwitter: String = "",
		@SerializedName("strInstagram") val strInstagram: String = "",
		@SerializedName("strDescriptionEN") val strDescriptionEN: String = "",
		@SerializedName("strDescriptionDE") val strDescriptionDE: String = "",
		@SerializedName("strDescriptionFR") val strDescriptionFR: Any = "",
		@SerializedName("strDescriptionCN") val strDescriptionCN: Any = "",
		@SerializedName("strDescriptionIT") val strDescriptionIT: String = "",
		@SerializedName("strDescriptionJP") val strDescriptionJP: Any = "",
		@SerializedName("strDescriptionRU") val strDescriptionRU: Any = "",
		@SerializedName("strDescriptionES") val strDescriptionES: Any = "",
		@SerializedName("strDescriptionPT") val strDescriptionPT: Any = "",
		@SerializedName("strDescriptionSE") val strDescriptionSE: Any = "",
		@SerializedName("strDescriptionNL") val strDescriptionNL: Any = "",
		@SerializedName("strDescriptionHU") val strDescriptionHU: Any = "",
		@SerializedName("strDescriptionNO") val strDescriptionNO: Any = "",
		@SerializedName("strDescriptionIL") val strDescriptionIL: Any = "",
		@SerializedName("strDescriptionPL") val strDescriptionPL: Any = "",
		@SerializedName("strGender") val strGender: String = "",
		@SerializedName("strCountry") val strCountry: String = "",
		@SerializedName("strTeamBadge") val strTeamBadge: String = "",
		@SerializedName("strTeamJersey") val strTeamJersey: String = "",
		@SerializedName("strTeamLogo") val strTeamLogo: String = "",
		@SerializedName("strTeamFanart1") val strTeamFanart1: String = "",
		@SerializedName("strTeamFanart2") val strTeamFanart2: String = "",
		@SerializedName("strTeamFanart3") val strTeamFanart3: String = "",
		@SerializedName("strTeamFanart4") val strTeamFanart4: String = "",
		@SerializedName("strTeamBanner") val strTeamBanner: String = "",
		@SerializedName("strYoutube") val strYoutube: String = "",
		@SerializedName("strLocked") val strLocked: String = ""
): Serializable