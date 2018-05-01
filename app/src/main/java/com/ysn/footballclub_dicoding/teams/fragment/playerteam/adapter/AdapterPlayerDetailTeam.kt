/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 4:36 AM
 */

package com.ysn.footballclub_dicoding.teams.fragment.playerteam.adapter

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
import com.ysn.footballclub_dicoding.model.teams.Player
import org.jetbrains.anko.*

class AdapterPlayerDetailTeam constructor(private var allPlayer: List<Player>,
                                          private val context: Context,
                                          private val listenerAdapterPlayerDetailTeam: ListenerAdapterPlayerDetailTeam) : RecyclerView.Adapter<AdapterPlayerDetailTeam.ViewHolderItemPlayerDetailTeam>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItemPlayerDetailTeam {
        return ViewHolderItemPlayerDetailTeam(
                ItemPlayerDetailTeamUI().createView(
                        AnkoContext.create(parent.context, parent)
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItemPlayerDetailTeam, position: Int) {
        val player = allPlayer[position]
        Glide.with(context)
                .load(player.strCutout)
                .into(holder.imageViewCutout)
        holder.textViewPlayerName.text = player.strPlayer
        holder.textViewPosition.text = player.strPosition
    }

    override fun getItemCount(): Int = allPlayer.size

    fun refreshData(allPlayer: List<Player>) {
        this.allPlayer = allPlayer
        notifyDataSetChanged()
    }

    inner class ViewHolderItemPlayerDetailTeam constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val relativeLayoutContainerItemPlayerDetailTeam: RelativeLayout = itemView
                .find(R.id.relative_layout_container_item_player_detail_team)
        val imageViewCutout: ImageView = itemView.find(R.id.image_view_cutout_item_player_detail_team)
        val textViewPlayerName: TextView = itemView.find(R.id.text_view_name_item_player_detail_team)
        val textViewPosition: TextView = itemView.find(R.id.text_view_position_item_player_detail_team)

        init {
            relativeLayoutContainerItemPlayerDetailTeam.setOnClickListener {
                listenerAdapterPlayerDetailTeam.onClickItemPlayer(playerItem =  allPlayer[adapterPosition])
            }
        }

    }

    interface ListenerAdapterPlayerDetailTeam {

        fun onClickItemPlayer(playerItem: Player)

    }

}

class ItemPlayerDetailTeamUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {

                imageView {
                    id = R.id.image_view_cutout_item_player_detail_team
                }.lparams {
                    width = dip(32)
                    height = dip(32)
                    centerVertically()
                    alignParentLeft()
                    rightMargin = dip(16)
                }

                textView {
                    id = R.id.text_view_name_item_player_detail_team
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    centerVertically()
                    rightOf(R.id.image_view_cutout_item_player_detail_team)
                    leftOf(R.id.text_view_position_item_player_detail_team)
                }

                textView {
                    id = R.id.text_view_position_item_player_detail_team
                    textColor = ContextCompat.getColor(ctx, android.R.color.darker_gray)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    alignParentRight()
                    centerVertically()
                }

                id = R.id.relative_layout_container_item_player_detail_team
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
