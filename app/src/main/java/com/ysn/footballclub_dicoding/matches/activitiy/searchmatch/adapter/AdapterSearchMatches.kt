/*
 * Created by YSN Studio on 4/26/18 11:09 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 10:38 PM
 */

package com.ysn.footballclub_dicoding.matches.activitiy.searchmatch.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.Event
import com.ysn.footballclub_dicoding.model.EventSearchLeague
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterSearchMatches constructor(private var events: List<EventSearchLeague>,
                                       private val listenerADapterSearchMatch: ListenerAdapterSearchesMatches) : RecyclerView.Adapter<AdapterSearchMatches.ViewHolderItemSearchMatch>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemSearchMatch {
        return ViewHolderItemSearchMatch(
                ItemSearchMatchUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemSearchMatch, position: Int) {
        val event = events[position]
        val timestampDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                .parse(event.dateEvent)
        val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)
                .format(timestampDateEvent)
        holder.textViewDateSchedule.text = dateSchedule
        holder.textViewHomeScore.text = event.intHomeScore
        holder.textViewHomeClubName.text = event.strHomeTeam
        holder.textViewAwayScore.text = event.intAwayScore
        holder.textViewAwayClubName.text = event.strAwayTeam
    }

    override fun getItemCount(): Int = events.size

    fun refreshData(events: List<EventSearchLeague>) {
        this.events = events
        notifyDataSetChanged()
    }

    inner class ViewHolderItemSearchMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemSearchMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_search_match)
        val textViewDateSchedule: TextView = itemView.find(R.id.text_view_date_schedule_item_search_match)
        val textViewHomeScore: TextView = itemView.find(R.id.text_view_home_score_item_search_match)
        val textViewAwayScore: TextView = itemView.find(R.id.text_view_away_score_item_search_match)
        val textViewHomeClubName: TextView = itemView.find(R.id.text_view_home_club_name_item_search_match)
        val textViewAwayClubName: TextView = itemView.find(R.id.text_view_away_club_name_item_search_match)

        init {
            relativeLayoutContainerItemSearchMatch.setOnClickListener {
                listenerADapterSearchMatch.onClickItemMatch(event = events[adapterPosition])
            }
        }
    }

    interface ListenerAdapterSearchesMatches {

        fun onClickItemMatch(event: EventSearchLeague)

    }

}

class ItemSearchMatchUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                textView {
                    id = R.id.text_view_date_schedule_item_search_match
                    text = ctx.getString(R.string.example_date_schedule)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    bottomMargin = dip(10)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_label_vs_item_search_match
                    text = ctx.getString(R.string.vs)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_search_match)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_home_score_item_search_match
                    text = "2"
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_search_match)
                    leftOf(R.id.text_view_label_vs_item_search_match)
                }

                textView {
                    id = R.id.text_view_away_score_item_search_match
                    text = "1"
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_search_match)
                    rightOf(R.id.text_view_label_vs_item_search_match)
                }

                textView {
                    id = R.id.text_view_home_club_name_item_search_match
                    text = "Brighton"
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_search_match)
                    leftOf(R.id.text_view_home_score_item_search_match)
                }

                textView {
                    id = R.id.text_view_away_club_name_item_search_match
                    text = "Arsenal"
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_search_match)
                    rightOf(R.id.text_view_away_score_item_search_match)
                }

                id = R.id.relative_layout_container_item_search_match
                topPadding = dip(16)
                rightPadding = dip(16)
                bottomPadding = dip(16)
                leftPadding = dip(16)
                lparams {
                    width = matchParent
                    height = wrapContent
                }

            }
        }
    }

}
