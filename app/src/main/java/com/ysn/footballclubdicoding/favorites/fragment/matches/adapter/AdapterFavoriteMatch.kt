/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:50 PM
 */

package com.ysn.footballclubdicoding.favorites.fragment.matches.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.db.EntityEvent
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterFavoriteMatch constructor(private var eventMatches: List<EntityEvent>,
                                       private val listenerAdapterFavoriteMatch: ListenerAdapterFavoriteMatch) : RecyclerView.Adapter<AdapterFavoriteMatch.ViewHolderItemFavoriteMatch>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemFavoriteMatch {
        return ViewHolderItemFavoriteMatch(
                ItemFavoriteMatchUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemFavoriteMatch, position: Int) {
        val event = eventMatches[position]
        val timestampdDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                .parse(event.dateEvent)
        val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US)
                .format(timestampdDateEvent)
        holder.textViewDateSchedule.text = dateSchedule
        holder.textViewHomeScore.text = event.intHomeScore
        holder.textViewHomeClubName.text = event.strHomeTeam
        holder.textViewAwayScore.text = event.intAwayScore
        holder.textViewAwayClubName.text = event.strAwayTeam
    }

    override fun getItemCount(): Int = eventMatches.size

    fun refreshData(eventMatches: List<EntityEvent>) {
        this.eventMatches = eventMatches
        notifyDataSetChanged()
    }

    inner class ViewHolderItemFavoriteMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemFavoriteMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_favorite_match)
        val textViewDateSchedule: TextView = itemView.find(R.id.text_view_date_schedule_item_favorite_match)
        val textViewHomeScore: TextView = itemView.find(R.id.text_view_home_score_item_favorite_match)
        val textViewAwayScore: TextView = itemView.find(R.id.text_view_away_score_item_favorite_match)
        val textViewHomeClubName: TextView = itemView.find(R.id.text_view_home_club_name_item_favorite_match)
        val textViewAwayClubName: TextView = itemView.find(R.id.text_view_away_club_name_item_favorite_match)

        init {
            relativeLayoutContainerItemFavoriteMatch.setOnClickListener {
                listenerAdapterFavoriteMatch.onClickItemMatch(eventMatches = eventMatches[adapterPosition])
            }
        }
    }

    interface ListenerAdapterFavoriteMatch {

        fun onClickItemMatch(eventMatches: EntityEvent)

    }

}

class ItemFavoriteMatchUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                textView {
                    id = R.id.text_view_date_schedule_item_favorite_match
                    text = ctx.getString(R.string.example_date_schedule)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    bottomMargin = dip(10)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_label_vs_item_favorite_match
                    text = ctx.getString(R.string.vs)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_favorite_match)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_home_score_item_favorite_match
                    text = "2"
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_favorite_match)
                    leftOf(R.id.text_view_label_vs_item_favorite_match)
                }

                textView {
                    id = R.id.text_view_away_score_item_favorite_match
                    text = "1"
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_favorite_match)
                    rightOf(R.id.text_view_label_vs_item_favorite_match)
                }

                textView {
                    id = R.id.text_view_home_club_name_item_favorite_match
                    text = ctx.getString(R.string.brighton)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_favorite_match)
                    leftOf(R.id.text_view_home_score_item_favorite_match)
                }

                textView {
                    id = R.id.text_view_away_club_name_item_favorite_match
                    text = ctx.getString(R.string.arsenal)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_favorite_match)
                    rightOf(R.id.text_view_away_score_item_favorite_match)
                }

                id = R.id.relative_layout_container_item_favorite_match
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
