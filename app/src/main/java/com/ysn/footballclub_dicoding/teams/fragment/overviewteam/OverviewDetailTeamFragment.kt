/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 11:34 PM
 */

package com.ysn.footballclub_dicoding.teams.fragment.overviewteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ysn.footballclub_dicoding.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class OverviewDetailTeamFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = OverviewDetailTeamUI()
                .createView(AnkoContext.create(ctx, this))
        doLoadData(view = view)
        return view
    }

    private fun doLoadData(view: View) {
        val overviewClub = arguments?.get("overview") as String
        val textViewOverviewDetailTeam: TextView = view.find(R.id.text_view_description_team_overview_detail_team)
        textViewOverviewDetailTeam.text = overviewClub
    }

}

class OverviewDetailTeamUI : AnkoComponent<OverviewDetailTeamFragment> {

    override fun createView(ui: AnkoContext<OverviewDetailTeamFragment>): View {
        return with(ui) {
            relativeLayout {

                scrollView {

                    textView {
                        id = R.id.text_view_description_team_overview_detail_team
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }

                    id = R.id.scroll_view_overview_detail_team
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }

                id = R.id.relative_layout_container_overview_detail_team
                lparams {
                    width = matchParent
                    height = wrapContent
                    topPadding = dip(16)
                    rightPadding = dip(16)
                    bottomPadding = dip(16)
                    leftPadding = dip(16)
                }

            }
        }
    }

}