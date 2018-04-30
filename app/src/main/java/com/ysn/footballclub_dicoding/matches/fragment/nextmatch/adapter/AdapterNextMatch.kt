/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 9:34 PM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.matches.EventMatches
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterNextMatch constructor(private var eventMatches: List<EventMatches>,
                                   private val listenerAdapterMatch: ListenerAdapterMatch) : RecyclerView.Adapter<AdapterNextMatch.ViewHolderItemNextMatch>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemNextMatch {
        return ViewHolderItemNextMatch(
                ItemNextMatchUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemNextMatch, position: Int) {
        val event = eventMatches[position]
        val timestampDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(event.dateEvent)
        val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US).format(timestampDateEvent)
        holder.textViewDateSchedule.text = dateSchedule
        holder.textViewHomeClubName.text = event.strHomeTeam
        holder.textViewAwayClubName.text = event.strAwayTeam
    }

    override fun getItemCount(): Int = eventMatches.size

    fun refreshData(eventMatches: List<EventMatches>) {
        this.eventMatches = eventMatches
        notifyDataSetChanged()
    }

    inner class ViewHolderItemNextMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemNextMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_next_match)
        private val imageViewAddEventCalendarItemNextMatch: ImageView = itemView
                .find(R.id.image_view_add_event_calendar_item_next_match)
        val textViewDateSchedule: TextView = itemView.find(R.id.text_view_date_schedule_item_next_match)
        val textViewHomeClubName: TextView = itemView.find(R.id.text_view_home_club_name_item_next_match)
        val textViewAwayClubName: TextView = itemView.find(R.id.text_view_away_club_name_item_next_match)

        init {
            relativeLayoutContainerItemNextMatch.setOnClickListener {
                listenerAdapterMatch.onClickItemMatch(eventMatches = eventMatches[adapterPosition])
            }
            imageViewAddEventCalendarItemNextMatch.setOnClickListener {
                listenerAdapterMatch.onAddMatchToCalendarEvent(eventMatches = eventMatches[adapterPosition])
            }
        }
    }

    interface ListenerAdapterMatch {

        fun onClickItemMatch(eventMatches: EventMatches)

        fun onAddMatchToCalendarEvent(eventMatches: EventMatches)

    }

}

class ItemNextMatchUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                textView {
                    id = R.id.text_view_date_schedule_item_next_match
                    text = ctx.getString(R.string.example_date_schedule)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    bottomMargin = dip(10)
                    centerHorizontally()
                }

                imageView {
                    id = R.id.image_view_add_event_calendar_item_next_match
                    image = ContextCompat.getDrawable(ctx, R.drawable.ic_add_alert_color_primary_24dp)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    alignParentRight()
                    centerVertically()
                }

                textView {
                    id = R.id.text_view_label_vs_item_next_match
                    text = ctx.getString(R.string.vs)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    rightMargin = dip(20)
                    leftMargin = dip(20)
                    below(R.id.text_view_date_schedule_item_next_match)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_home_club_name_item_next_match
                    text = "Brighton"
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_next_match)
                    leftOf(R.id.text_view_label_vs_item_next_match)
                }

                textView {
                    id = R.id.text_view_away_club_name_item_next_match
                    text = "Arsenal"
                    textSize = 16f
                    ellipsize = TextUtils.TruncateAt.END
                    singleLine = true
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_next_match)
                    rightOf(R.id.text_view_label_vs_item_next_match)
                    leftOf(R.id.image_view_add_event_calendar_item_next_match)
                }

                id = R.id.relative_layout_container_item_next_match
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

