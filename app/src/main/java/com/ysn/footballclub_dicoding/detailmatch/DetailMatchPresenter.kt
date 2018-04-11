/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 2:13 PM
 */

package com.ysn.footballclub_dicoding.detailmatch

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class DetailMatchPresenter constructor(private val view: DetailMatchView){

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

}