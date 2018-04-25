/*
 * Created by YSN Studio on 4/26/18 3:27 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/26/18 3:26 AM
 */

package com.ysn.footballclub_dicoding.matches.fragment.nextmatch.adapter

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.Event
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.util.*

class AdapterNextMatch constructor(private var events: List<Event>,
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
        val event = events[position]
        val timestampDateEvent = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(event.dateEvent)
        val dateSchedule = SimpleDateFormat("EEE, dd MMM yyyy", Locale.US).format(timestampDateEvent)
        holder.textViewDateSchedule.text = dateSchedule
        holder.textViewHomeClubName.text = event.strHomeTeam
        holder.textViewAwayClubName.text = event.strAwayTeam
    }

    override fun getItemCount(): Int = events.size

    fun refreshData(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    inner class ViewHolderItemNextMatch constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemNextMatch: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_next_match)
        val textViewDateSchedule: TextView = itemView.find(R.id.text_view_date_schedule_item_next_match)
        val textViewHomeClubName: TextView = itemView.find(R.id.text_view_home_club_name_item_next_match)
        val textViewAwayClubName: TextView = itemView.find(R.id.text_view_away_club_name_item_next_match)

        init {
            relativeLayoutContainerItemNextMatch.setOnClickListener {
                listenerAdapterMatch.onClickItemMatch(event = events[adapterPosition])
            }
        }
    }

    interface ListenerAdapterMatch {

        fun onClickItemMatch(event: Event)

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
                    image = ContextCompat.getDrawable(ctx, R.drawable.ic_add_alert_accent_24dp)
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

