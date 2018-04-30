/*
 * Created by YSN Studio on 4/30/18 10:22 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/30/18 10:07 PM
 */

package com.ysn.footballclub_dicoding.teams.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.teams.AllTeamItem
import org.jetbrains.anko.*

class AdapterTeams constructor(private var allTeams: List<AllTeamItem>,
                               private val context: Context,
                               private val listenerAdapterTeam: ListenerAdapterTeam) : RecyclerView.Adapter<AdapterTeams.ViewHolderItemAllTeam>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemAllTeam {
        return ViewHolderItemAllTeam(
                ItemTeamUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemAllTeam, position: Int) {
        val itemAllTeam = allTeams[position]
        Glide.with(context)
                .load(itemAllTeam.strTeamBadge)
                .into(holder.imageViewLogoClubItemAllTeam)
        holder.textViewClubNameItemAllTeam.text = itemAllTeam.strTeam
    }

    override fun getItemCount(): Int = allTeams.size

    fun refreshData(allTeams: MutableList<AllTeamItem>) {
        this.allTeams = allTeams
        notifyDataSetChanged()
    }

    inner class ViewHolderItemAllTeam constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemAllTeam: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_all_team)
        val imageViewLogoClubItemAllTeam: ImageView = itemView
                .find(R.id.image_view_logo_club_item_all_team)
        val textViewClubNameItemAllTeam: TextView = itemView
                .find(R.id.text_view_club_name_item_all_team)

        init {
            relativeLayoutContainerItemAllTeam.setOnClickListener {
                listenerAdapterTeam.onClickItemTeam(allTeamItem = allTeams[adapterPosition])
            }
        }

    }

    interface ListenerAdapterTeam {

        fun onClickItemTeam(allTeamItem: AllTeamItem)

    }

}

class ItemTeamUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                imageView {
                    id = R.id.image_view_logo_club_item_all_team
                }.lparams {
                    width = dip(32)
                    height = dip(32)
                    centerVertically()
                }

                textView {
                    id = R.id.text_view_club_name_item_all_team
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    centerVertically()
                    rightOf(R.id.image_view_logo_club_item_all_team)
                    leftMargin = dip(16)
                }

                id = R.id.relative_layout_container_item_all_team
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