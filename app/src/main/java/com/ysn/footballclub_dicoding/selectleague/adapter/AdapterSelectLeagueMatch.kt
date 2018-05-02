/*
 * Created by YSN Studio on 5/2/18 9:57 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/29/18 11:02 PM
 */

package com.ysn.footballclub_dicoding.selectleague.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclub_dicoding.R
import org.jetbrains.anko.*

class AdapterSelectLeagueMatch(private val arrayLeague: Array<String>,
                               private val arrayLeagueId: IntArray,
                               private val listenerAdapterSelectLeagueMatch: ListenerAdapterSelectLeagueMatch) : RecyclerView.Adapter<AdapterSelectLeagueMatch.ViewHolderItemSelectLeagueMatch>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemSelectLeagueMatch {
        return ViewHolderItemSelectLeagueMatch(
                ItemSelectLeagueMatchUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemSelectLeagueMatch, position: Int) {
        val leagueName = arrayLeague[position]
        holder.textViewLeagueNameItemSelectLeagueMatch.text = leagueName
    }

    override fun getItemCount(): Int = arrayLeagueId.size

    inner class ViewHolderItemSelectLeagueMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val relativeLayoutContainerItemSelectLeagueMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_select_league_match)
        val textViewLeagueNameItemSelectLeagueMatch: TextView = itemView
                .find(R.id.text_view_league_name_item_select_league_match)

        init {
            relativeLayoutContainerItemSelectLeagueMatch.setOnClickListener {
                listenerAdapterSelectLeagueMatch.onClickItemLeagueMatch(
                        leagueName = arrayLeague[adapterPosition],
                        idLeague = arrayLeagueId[adapterPosition]
                )
            }
        }
    }

    interface ListenerAdapterSelectLeagueMatch {

        fun onClickItemLeagueMatch(leagueName: String, idLeague: Int)

    }

}

class ItemSelectLeagueMatchUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                textView {
                    id = R.id.text_view_league_name_item_select_league_match
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                }

                id = R.id.relative_layout_container_item_select_league_match
                lparams {
                    width = matchParent
                    height = wrapContent
                    topPadding = dip(16)
                    bottomPadding = dip(16)
                }
            }
        }
    }

}