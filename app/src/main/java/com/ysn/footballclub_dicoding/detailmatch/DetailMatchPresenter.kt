/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 1:08 PM
 */

package com.ysn.footballclub_dicoding.detailmatch

import android.content.Entity
import android.database.sqlite.SQLiteConstraintException
import com.ysn.footballclub_dicoding.db.DatabaseOpenHelper
import com.ysn.footballclub_dicoding.db.EntityEvent
import com.ysn.footballclub_dicoding.model.Event
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.info
import org.json.JSONObject
import java.io.IOException

class DetailMatchPresenter constructor(private val view: DetailMatchView) : AnkoLogger {

    fun onLoadImageClub(idHomeTeam: String, idAwayTeam: String) {
        val observableHomeTeam = Observable
                .create { emitter: ObservableEmitter<Map<String, Any>> ->
                    val url = "https://www.thesportsdb.com/api/v1/json/4012853/lookupteam.php?id=$idHomeTeam"
                    val request = Request.Builder()
                            .url(url)
                            .build()
                    OkHttpClient.Builder()
                            .build()
                            .newCall(request)
                            .enqueue(object : Callback {
                                override fun onFailure(call: Call?, e: IOException) {
                                    emitter.onError(e)
                                }

                                override fun onResponse(call: Call?, response: Response) {
                                    val mapData = HashMap<String, Any>()
                                    val responseCode = response.code()
                                    val responseMessage = response.message()
                                    if (responseCode == 200) {
                                        val jsonObjectResponse = JSONObject(response.body()?.string())
                                        val jsonArrayTeam = jsonObjectResponse.getJSONArray("teams")
                                        val jsonObjectTeam = jsonArrayTeam[0] as JSONObject
                                        mapData["error"] = false
                                        mapData["logo"] = jsonObjectTeam.getString("strTeamBadge")
                                    } else {
                                        mapData["error"] = true
                                        mapData["message"] = responseMessage
                                    }
                                    emitter.onNext(mapData)
                                    emitter.onComplete()
                                }
                            })
                }

        val observableAwayTeam = Observable
                .create { emitter: ObservableEmitter<Map<String, Any>> ->
                    val url = "https://www.thesportsdb.com/api/v1/json/4012853/lookupteam.php?id=$idAwayTeam"
                    info { "url: $url" }
                    val request = Request.Builder()
                            .url(url)
                            .build()
                    OkHttpClient.Builder()
                            .build()
                            .newCall(request)
                            .enqueue(object : Callback {
                                override fun onFailure(call: Call?, e: IOException) {
                                    emitter.onError(e)
                                }

                                override fun onResponse(call: Call?, response: Response) {
                                    val mapData = HashMap<String, Any>()
                                    val responseCode = response.code()
                                    val responseMessage = response.message()
                                    if (responseCode == 200) {
                                        val jsonObjectResponse = JSONObject(response.body()?.string())
                                        val jsonArrayTeam = jsonObjectResponse.getJSONArray("teams")
                                        val jsonObjectTeam = jsonArrayTeam[0] as JSONObject
                                        mapData["error"] = false
                                        mapData["logo"] = jsonObjectTeam.getString("strTeamBadge")
                                    } else {
                                        mapData["error"] = true
                                        mapData["message"] = responseMessage
                                    }
                                    emitter.onNext(mapData)
                                    emitter.onComplete()
                                }
                            })
                }

        Observable
                .zip(
                        observableHomeTeam,
                        observableAwayTeam,
                        BiFunction<Map<String, Any>, Map<String, Any>, Map<String, Any>> { t1, t2 ->
                            val mapResult = HashMap<String, Any>()
                            val errorDataHome = t1["error"] as Boolean
                            val errorDataAway = t2["error"] as Boolean
                            when {
                                errorDataHome -> t1
                                errorDataAway -> t2
                                else -> {
                                    mapResult["error"] = false
                                    mapResult["homeLogo"] = t1["logo"] as String
                                    mapResult["awayLogo"] = t2["logo"] as String
                                    mapResult
                                }

                            }
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            val error = it["error"] as Boolean
                            if (error) {
                                view.loadImageClubFailed(message = it["message"] as String)
                            } else {
                                val homeLogo = it["homeLogo"] as String
                                val awayLogo = it["awayLogo"] as String
                                view.loadImageClub(homeLogo = homeLogo, awayLogo = awayLogo)
                            }
                        },
                        {
                            it.printStackTrace()
                            view.loadImageClubFailed(message = it.message!!)
                        },
                        {
                            /* nothing to do in here */
                        }
                )

    }

    fun onAddFavoriteMatch(event: Event, database: DatabaseOpenHelper) {
        try {
            database.use {
                insert(EntityEvent.TABLE_EVENT,
                        EntityEvent.ID_EVENT to event.idEvent,
                        EntityEvent.DATE_EVENT to event.dateEvent,
                        EntityEvent.ID_HOME_TEAM to event.idHomeTeam,
                        EntityEvent.ID_AWAY_TEAM to event.idAwayTeam,
                        EntityEvent.INT_HOME_SCORE to event.intHomeScore,
                        EntityEvent.INT_AWAY_SCORE to event.intAwayScore,
                        EntityEvent.STR_HOME_TEAM to event.strHomeTeam,
                        EntityEvent.STR_AWAY_TEAM to event.strAwayTeam,
                        EntityEvent.STR_HOME_FORMATION to event.strHomeFormation,
                        EntityEvent.STR_AWAY_FORMATION to event.strAwayFormation,
                        EntityEvent.STR_HOME_GOAL_DETAILS to event.strHomeGoalDetails,
                        EntityEvent.STR_AWAY_GOAL_DETAILS to event.strAwayGoalDetails,
                        EntityEvent.INT_HOME_SHOTS to event.intHomeShots,
                        EntityEvent.INT_AWAY_SHOTS to event.intAwayShots,
                        EntityEvent.STR_HOME_LINEUP_GOAL_KEEPER to event.strHomeLineupGoalkeeper,
                        EntityEvent.STR_AWAY_LINEUP_GOAL_KEEPER to event.strAwayLineupGoalkeeper,
                        EntityEvent.STR_HOME_LINEUP_DEFENSE to event.strHomeLineupDefense,
                        EntityEvent.STR_AWAY_LINEUP_DEFENSE to event.strAwayLineupDefense,
                        EntityEvent.STR_HOME_LINEUP_MIDFIELD to event.strHomeLineupMidfield,
                        EntityEvent.STR_AWAY_LINEUP_MIDFIELD to event.strAwayLineupMidfield,
                        EntityEvent.STR_HOME_LINEUP_FORWARD to event.strHomeLineupForward,
                        EntityEvent.STR_AWAY_LINEUP_FORWARD to event.strAwayLineupForward,
                        EntityEvent.STR_HOME_LINEUP_SUBSTITUTES to event.strHomeLineupSubstitutes,
                        EntityEvent.STR_AWAY_LINEUP_SUBSTITUTES to event.strAwayLineupSubstitutes
                )
            }
            view.addFavoriteMatch()
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            view.addFavoriteMatchFailed(e.localizedMessage)
        }
    }

    fun onLoadData(database: DatabaseOpenHelper, idEvent: String) {
        try {
            database.use {
                select(EntityEvent.TABLE_EVENT)
                        .whereArgs("(${EntityEvent.ID_EVENT} = {idEvent})",
                                "idEvent" to idEvent).exec {
                            view.loadData(count = count)
                        }
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            view.loadDataFailed(e.localizedMessage)
        }
    }

    fun onDeleteFavoriteMatch(database: DatabaseOpenHelper, event: Event) {
        try {
            database.use {
                delete(EntityEvent.TABLE_EVENT,
                        "(${EntityEvent.ID_EVENT} = {idEvent})",
                        "idEvent" to event.idEvent)
            }
            view.deleteFavoriteMatch()
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            view.deleteFavoriteMatchFailed(e.localizedMessage)
        }
    }

}