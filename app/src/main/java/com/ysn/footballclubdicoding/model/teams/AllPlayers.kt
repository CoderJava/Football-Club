/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding.model.teams

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AllPlayers(
        @SerializedName("player") val player: List<Player>
)

data class Player(
        @SerializedName("idPlayer") val idPlayer: String = "",
        @SerializedName("idTeam") val idTeam: String = "",
        @SerializedName("idSoccerXML") val idSoccerXML: String = "",
        @SerializedName("idPlayerManager") val idPlayerManager: String = "",
        @SerializedName("strNationality") val strNationality: String = "",
        @SerializedName("strPlayer") val strPlayer: String = "",
        @SerializedName("strTeam") val strTeam: String = "",
        @SerializedName("strSport") val strSport: String = "",
        @SerializedName("intSoccerXMLTeamID") val intSoccerXMLTeamID: String = "",
        @SerializedName("dateBorn") val dateBorn: String = "",
        @SerializedName("dateSigned") val dateSigned: String = "",
        @SerializedName("strSigning") val strSigning: String = "",
        @SerializedName("strWage") val strWage: String = "",
        @SerializedName("strBirthLocation") val strBirthLocation: String = "",
        @SerializedName("strDescriptionEN") val strDescriptionEN: String = "",
        @SerializedName("strDescriptionDE") val strDescriptionDE: Any = "",
        @SerializedName("strDescriptionFR") val strDescriptionFR: Any = "",
        @SerializedName("strDescriptionCN") val strDescriptionCN: Any = "",
        @SerializedName("strDescriptionIT") val strDescriptionIT: Any = "",
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
        @SerializedName("strPosition") val strPosition: String = "",
        @SerializedName("strCollege") val strCollege: Any = "",
        @SerializedName("strFacebook") val strFacebook: String = "",
        @SerializedName("strWebsite") val strWebsite: String = "",
        @SerializedName("strTwitter") val strTwitter: String = "",
        @SerializedName("strInstagram") val strInstagram: String = "",
        @SerializedName("strYoutube") val strYoutube: String = "",
        @SerializedName("strHeight") val strHeight: String = "",
        @SerializedName("strWeight") val strWeight: String = "",
        @SerializedName("intLoved") val intLoved: String = "",
        @SerializedName("strThumb") val strThumb: String = "",
        @SerializedName("strCutout") val strCutout: String = "",
        @SerializedName("strFanart1") val strFanart1: String = "",
        @SerializedName("strFanart2") val strFanart2: String = "",
        @SerializedName("strFanart3") val strFanart3: String = "",
        @SerializedName("strFanart4") val strFanart4: String = "",
        @SerializedName("strLocked") val strLocked: String = ""
) : Serializable