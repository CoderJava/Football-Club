/*
 * Created by YSN Studio on 5/1/18 11:50 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 11:44 PM
 */

package com.ysn.footballclub_dicoding.favorites.fragment.teams.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.db.EntityTeam
import org.jetbrains.anko.*

class AdapterFavoriteTeam constructor(private var allTeams: List<EntityTeam>,
                                      private val context: Context,
                                      private val listenerAdapterFavoriteTeam: ListenerAdapterFavoriteTeam): RecyclerView.Adapter<AdapterFavoriteTeam.ViewHolderItemFavoriteTeam>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemFavoriteTeam {
        return ViewHolderItemFavoriteTeam(
                ItemFavoriteTeam().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemFavoriteTeam, position: Int) {
        val itemAllTeam = allTeams[position]
        Glide.with(context)
                .load(itemAllTeam.strTeamBadge)
                .into(holder.imageViewLogoClubItemFavoriteTeam)
        holder.textViewClubNameItemFavoriteTeam.text = itemAllTeam.strTeam
    }

    override fun getItemCount(): Int = allTeams.size

    fun refreshData(allTeams: MutableList<EntityTeam>) {
        this.allTeams = allTeams
        notifyDataSetChanged()
    }

    inner class ViewHolderItemFavoriteTeam constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemFavoriteTeam: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_favorite_team)
        val imageViewLogoClubItemFavoriteTeam: ImageView = itemView
                .find(R.id.image_view_logo_club_item_favorite_team)
        val textViewClubNameItemFavoriteTeam: TextView = itemView
                .find(R.id.text_view_club_name_item_favorite_team)

        init {
            relativeLayoutContainerItemFavoriteTeam.setOnClickListener {
                listenerAdapterFavoriteTeam.onClickTeam(entityTeam = allTeams[adapterPosition])
            }
        }
    }

    interface ListenerAdapterFavoriteTeam {

        fun onClickTeam(entityTeam: EntityTeam)

    }

}

class ItemFavoriteTeam : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                imageView {
                    id = R.id.image_view_logo_club_item_favorite_team
                }.lparams {
                    width = dip(32)
                    height = dip(32)
                    centerVertically()
                }

                textView {
                    id = R.id.text_view_club_name_item_favorite_team
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    centerVertically()
                    rightOf(R.id.image_view_logo_club_item_favorite_team)
                    leftMargin = dip(16)
                }

                id = R.id.relative_layout_container_item_favorite_team
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
