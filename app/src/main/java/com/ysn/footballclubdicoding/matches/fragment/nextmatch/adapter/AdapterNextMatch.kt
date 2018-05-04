/*
 * Created by YSN Studio on 5/4/18 9:37 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/4/18 9:36 AM
 */

package com.ysn.footballclubdicoding.matches.fragment.nextmatch.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclubdicoding.R
import com.ysn.footballclubdicoding.model.matches.EventMatches
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
        val dateTimeGmt = toGMTFormat(date = event.strDate, time = event.strTime)
        val formatterDateTimeGmt = SimpleDateFormat("dd/MM/yyyy HH:mm")
                .format(dateTimeGmt)
        holder.textViewDateSchedule.text = formatterDateTimeGmt.split(" ")[0]
        holder.textViewTimeSchedule.text = formatterDateTimeGmt.split(" ")[1]
        holder.textViewHomeClubName.text = event.strHomeTeam
        holder.textViewAwayClubName.text = event.strAwayTeam
    }

    override fun getItemCount(): Int = eventMatches.size

    fun refreshData(eventMatches: List<EventMatches>) {
        this.eventMatches = eventMatches
        notifyDataSetChanged()
    }

    fun toGMTFormat(date: String, time: String): Date? {
        val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }

    inner class ViewHolderItemNextMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemNextMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_next_match)
        private val imageViewAddEventCalendarItemNextMatch: ImageView = itemView
                .find(R.id.image_view_add_event_calendar_item_next_match)
        val textViewDateSchedule: TextView = itemView.find(R.id.text_view_date_schedule_item_next_match)
        val textViewTimeSchedule: TextView = itemView.find(R.id.text_view_time_schedule_item_next_match)
        val textViewHomeClubName: TextView = itemView.find(R.id.text_view_home_club_name_item_next_match)
        val textViewAwayClubName: TextView = itemView.find(R.id.text_view_away_club_name_item_next_match)

        init {
            relativeLayoutContainerItemNextMatch.setOnClickListener {
                listenerAdapterMatch.onClickItemMatch(eventMatches = eventMatches[adapterPosition])
            }
            imageViewAddEventCalendarItemNextMatch.setOnClickListener {
                val eventMatches = eventMatches[adapterPosition]
                val dateTimeGmt = toGMTFormat(date = eventMatches.strDate, time = eventMatches.strTime)
                val formatterDateTimeGmt = SimpleDateFormat("dd/MM/yyyy HH:mm")
                        .format(dateTimeGmt)
                listenerAdapterMatch.onAddMatchToCalendarEvent(eventMatches = eventMatches, formatterDateTimeGmt = formatterDateTimeGmt)
            }
        }
    }

    interface ListenerAdapterMatch {

        fun onClickItemMatch(eventMatches: EventMatches)

        fun onAddMatchToCalendarEvent(eventMatches: EventMatches, formatterDateTimeGmt: String)

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
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_time_schedule_item_next_match
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_date_schedule_item_next_match)
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
                    below(R.id.text_view_time_schedule_item_next_match)
                    centerHorizontally()
                }

                textView {
                    id = R.id.text_view_home_club_name_item_next_match
                    text = ctx.getString(R.string.brighton)
                    textSize = 16f
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_time_schedule_item_next_match)
                    leftOf(R.id.text_view_label_vs_item_next_match)
                }

                textView {
                    id = R.id.text_view_away_club_name_item_next_match
                    text = ctx.getString(R.string.arsenal)
                    textSize = 16f
                    ellipsize = TextUtils.TruncateAt.END
                    singleLine = true
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    below(R.id.text_view_time_schedule_item_next_match)
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

