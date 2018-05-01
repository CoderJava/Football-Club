/*
 * Created by YSN Studio on 5/1/18 5:34 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 5:33 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.searchteam.adapter

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

class AdapterSearchTeams constructor(private var allTeams: List<AllTeamItem>,
                                     private val context: Context,
                                     private val listenerAdapterSearchTeam: ListenerAdapterSearchTeam) : RecyclerView.Adapter<AdapterSearchTeams.ViewHolderItemSearchAllTeam>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemSearchAllTeam {
        return ViewHolderItemSearchAllTeam(
                ItemSearchTeamUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemSearchAllTeam, position: Int) {
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

    inner class ViewHolderItemSearchAllTeam constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemAllTeam: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_search_team)
        val imageViewLogoClubItemAllTeam: ImageView = itemView
                .find(R.id.image_view_logo_club_item_search_team)
        val textViewClubNameItemAllTeam: TextView = itemView
                .find(R.id.text_view_club_name_item_search_team)

        init {
            relativeLayoutContainerItemAllTeam.setOnClickListener {
                listenerAdapterSearchTeam.onClickItemTeam(allTeamItem = allTeams[adapterPosition])
            }
        }
    }

    interface ListenerAdapterSearchTeam {

        fun onClickItemTeam(allTeamItem: AllTeamItem)

    }

}

class ItemSearchTeamUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                imageView {
                    id = R.id.image_view_logo_club_item_search_team
                }.lparams {
                    width = dip(32)
                    height = dip(32)
                    centerVertically()
                }

                textView {
                    id = R.id.text_view_club_name_item_search_team
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    centerVertically()
                    rightOf(R.id.image_view_logo_club_item_search_team)
                    leftMargin = dip(16)
                }

                id = R.id.relative_layout_container_item_search_team
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
