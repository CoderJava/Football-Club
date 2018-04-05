/*
 * Created by YSN Studio on 4/5/18 9:47 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/5/18 9:36 AM
 */

package com.ysn.footballclub_dicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ysn.footballclub_dicoding.adapter.AdapterFootballClub
import com.ysn.footballclub_dicoding.model.Football
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private val recyclerViewFootballClub by lazy {
        find<RecyclerView>(R.id.recycler_view_football_club_main_activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        val footballs = ArrayList<Football>()
        footballs.add(Football(name = "Barcelona FC", image = R.drawable.img_barca, detail = getString(R.string.barcelona_detail)))
        footballs.add(Football(name = "Real Madrid FC", image = R.drawable.img_madrid, detail = getString(R.string.madrid_detail)))
        footballs.add(Football(name = "Bayern Munchen FC", image = R.drawable.img_bayern, detail = getString(R.string.bayern_detail)))
        footballs.add(Football(name = "Manchester City FC", image = R.drawable.img_city, detail = getString(R.string.city_detail)))
        footballs.add(Football(name = "Manchester United FC", image = R.drawable.img_mu, detail = getString(R.string.mu_detail)))
        footballs.add(Football(name = "Chelsea FC", image = R.drawable.img_chelsea, detail = getString(R.string.chelsea_detail)))
        footballs.add(Football(name = "AC Milan FC", image = R.drawable.img_acm, detail = getString(R.string.acm_detail)))
        footballs.add(Football(name = "Arsenal FC", image = R.drawable.img_arsenal, detail = getString(R.string.arsenal_detail)))
        val adapterFootballClub = AdapterFootballClub(context = this, footballs = footballs, listenerAdapterFootballClub = object : AdapterFootballClub.ListenerAdapterFootballClub {
            override fun onClick(football: Football) {
                startActivity(intentFor<DetailClubActivity>("data" to football))
            }
        })
        recyclerViewFootballClub.layoutManager = LinearLayoutManager(this)
        recyclerViewFootballClub.adapter = adapterFootballClub
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            relativeLayout {
                recyclerView {
                    id = R.id.recycler_view_football_club_main_activity
                }.lparams(width = matchParent, height = wrapContent)
            }
        }
    }

}

