/*
 * Created by YSN Studio on 4/5/18 9:47 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/4/18 7:14 PM
 */

package com.ysn.footballclub_dicoding.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.Football
import org.jetbrains.anko.*

class AdapterFootballClub constructor(private val context: Context, private val footballs: ArrayList<Football>, private val listenerAdapterFootballClub: ListenerAdapterFootballClub) : RecyclerView.Adapter<AdapterFootballClub.ViewHolderItemFootballClub>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemFootballClub {
        return ViewHolderItemFootballClub(
                ItemFootballClubUI().createView(
                        AnkoContext.Companion.create(parent!!.context, parent)
                )
        )
    }

    override fun getItemCount(): Int = footballs.size

    override fun onBindViewHolder(holder: ViewHolderItemFootballClub, position: Int) {
        val football = footballs[position]
        holder.textViewNameFootballClub.text = football.name
        Glide.with(context)
                .load(football.image)
                .into(holder.imageViewFootballClub)
    }

    inner class ViewHolderItemFootballClub(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val relativeLayoutContainerItemFootballClub: RelativeLayout = itemView.find(R.id.relative_layout_container_item_football_club)
        val imageViewFootballClub: ImageView = itemView.find(R.id.image_view_item_football_club)
        val textViewNameFootballClub: TextView = itemView.find(R.id.text_view_item_football_club)

        init {
            relativeLayoutContainerItemFootballClub.setOnClickListener {
                listenerAdapterFootballClub.onClick(football = footballs[adapterPosition])
            }
        }

    }

    interface ListenerAdapterFootballClub {

        fun onClick(football: Football)

    }

}

class ItemFootballClubUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            imageView {
                id = R.id.image_view_item_football_club
                setImageDrawable(ContextCompat.getDrawable(context, R.drawable.img_acm))
            }.lparams(width = 96, height = 96) {
                rightMargin = dip(8)
            }

            textView {
                id = R.id.text_view_item_football_club
                text = "Club name"
            }.lparams {
                width = wrapContent
                height = wrapContent
                centerVertically()
                rightOf(R.id.image_view_item_football_club)
            }

            id = R.id.relative_layout_container_item_football_club
            lparams(width = matchParent, height = wrapContent) {
                topPadding = dip(8)
                rightPadding = dip(16)
                bottomPadding = dip(8)
                leftPadding = dip(16)
            }
        }
    }
}