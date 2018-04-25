/*
 * Created by YSN Studio on 4/26/18 4:50 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 4:47 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.selectleaguematch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.matches.fragment.selectleaguematch.adapter.AdapterSelectLeagueMatch
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SelectLeagueMatchActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName
    private lateinit var adapterSelectLeagueMatch: AdapterSelectLeagueMatch
    private val recyclerViewSelectLeagueMatch: RecyclerView by lazy {
        find<RecyclerView>(R.id.recycler_view_select_league_match_activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SelectLeagueMatchUI().setContentView(this)
        doLoadData()
    }

    private fun doLoadData() {
        val arrayLeague = resources.getStringArray(R.array.array_league)
        val arrayLeagueId = resources.getIntArray(R.array.array_league_id)
        adapterSelectLeagueMatch = AdapterSelectLeagueMatch(
                arrayLeague = arrayLeague,
                arrayLeagueId = arrayLeagueId,
                listenerAdapterSelectLeagueMatch = object : AdapterSelectLeagueMatch.ListenerAdapterSelectLeagueMatch {
                    override fun onClickItemLeagueMatch(leagueName: String, idLeague: Int) {
                        val intentResult = Intent()
                        intentResult.putExtra("idLeague", idLeague)
                        intentResult.putExtra("leagueName", leagueName)
                        setResult(Activity.RESULT_OK, intentResult)
                        finish()
                    }
                }
        )
        recyclerViewSelectLeagueMatch.addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
        recyclerViewSelectLeagueMatch.adapter = adapterSelectLeagueMatch
    }
}

class SelectLeagueMatchUI : AnkoComponent<SelectLeagueMatchActivity> {

    override fun createView(ui: AnkoContext<SelectLeagueMatchActivity>): View {
        return with(ui) {
            relativeLayout {

                recyclerView {
                    id = R.id.recycler_view_select_league_match_activity
                    layoutManager = LinearLayoutManager(ctx)
                }.lparams {
                    width = matchParent
                    height = matchParent
                    rightPadding = dip(16)
                    leftPadding = dip(16)
                }

                id = R.id.relative_layout_container_select_league_match_activity
                lparams {
                    width = matchParent
                    height = matchParent
                }
            }
        }
    }

}
