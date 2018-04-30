/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.fragment.previousmatch.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.matches.EventMatches
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterPreviousMatch constructor(private var eventMatches: List<EventMatches>,
                                       private val listenerAdapterMatch: ListenerAdapterMatch) : RecyclerView.Adapter<AdapterPreviousMatch.ViewHolderItemPreviousMatch>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemPreviousMatch {
        return ViewHolderItemPreviousMatch(
                ItemPreviousMatchUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemPreviousMatch, position: Int) {
        val event = eventMatches[position]
        val timestampDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(event.dateEvent)
        val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US).format(timestampDateEvent)
        holder.textViewDateSchedule.text = dateSchedule
        holder.textViewHomeScore.text = event.intHomeScore
        holder.textViewHomeClubName.text = event.strHomeTeam
        holder.textViewAwayScore.text = event.intAwayScore
        holder.textViewAwayClubName.text = event.strAwayTeam
    }

    fun refreshData(eventMatches: List<EventMatches>) {
        this.eventMatches = eventMatches
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = eventMatches.size

    inner class ViewHolderItemPreviousMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItmePreviousMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_previous_match)
        val textViewDateSchedule: TextView = itemView.find(R.id.text_view_date_schedule_item_previous_match)
        val textViewHomeScore: TextView = itemView.find(R.id.text_view_home_score_item_previous_match)
        val textViewAwayScore: TextView = itemView.find(R.id.text_view_away_score_item_previous_match)
        val textViewHomeClubName: TextView = itemView.find(R.id.text_view_home_club_name_item_previous_match)
        val textViewAwayClubName: TextView = itemView.find(R.id.text_view_away_club_name_item_previous_match)

        init {
            relativeLayoutContainerItmePreviousMatch.setOnClickListener {
                listenerAdapterMatch.onClickItemMatch(eventMatches = eventMatches[adapterPosition])
            }
        }

    }

    interface ListenerAdapterMatch {

        fun onClickItemMatch(eventMatches: EventMatches)

    }

}

class ItemPreviousMatchUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                textView {
                    id = R.id.text_view_date_schedule_item_previous_match
                    text = ctx.getString(R.string.example_date_schedule)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    bottomMargin = dip(10)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_label_vs_item_previous_match
                    text = ctx.getString(R.string.vs)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_previous_match)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_home_score_item_previous_match
                    text = "2"
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_previous_match)
                    leftOf(R.id.text_view_label_vs_item_previous_match)
                }

                textView {
                    id = R.id.text_view_away_score_item_previous_match
                    text = "1"
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_previous_match)
                    rightOf(R.id.text_view_label_vs_item_previous_match)
                }

                textView {
                    id = R.id.text_view_home_club_name_item_previous_match
                    text = "Brighton"
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_previous_match)
                    leftOf(R.id.text_view_home_score_item_previous_match)
                }

                textView {
                    id = R.id.text_view_away_club_name_item_previous_match
                    text = "Arsenal"
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_previous_match)
                    rightOf(R.id.text_view_away_score_item_previous_match)
                }

                id = R.id.relative_layout_container_item_previous_match
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