/*
 * Created by YSN Studio on 5/1/18 4:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 4:31 PM
 */

package com.ysn.footballclub_dicoding.teams.activity.detailplayer

import android.os.Bundle
import android.support.design.R.attr.colorPrimaryDark
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.model.teams.Player
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailPlayerActivity : AppCompatActivity(), AnkoLogger {

    private val TAG = javaClass.simpleName
    private lateinit var imageViewThumbnailPlayer: ImageView
    private lateinit var textViewWeightPlayer: TextView
    private lateinit var textViewHeightPlayer: TextView
    private lateinit var textViewPositionPlayer: TextView
    private lateinit var textViewDescriptionPlayer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailPlayerUI().setContentView(this)
        initViews()
        doLoadData()
    }

    private fun doLoadData() {
        val bundle = intent.extras
        val player = bundle?.getSerializable("playerItem") as Player
        Glide.with(ctx)
                .load(player.strThumb)
                .into(imageViewThumbnailPlayer)
        textViewWeightPlayer.text = player.strWeight
        textViewHeightPlayer.text = player.strHeight
        textViewPositionPlayer.text = player.strPosition
        textViewDescriptionPlayer.text = player.strDescriptionEN
    }

    private fun initViews() {
        imageViewThumbnailPlayer = find(R.id.image_view_thumb_detail_player)
        textViewWeightPlayer = find(R.id.text_view_weight_detail_player)
        textViewHeightPlayer = find(R.id.text_view_height_detail_player)
        textViewPositionPlayer = find(R.id.text_view_position_detail_player)
        textViewDescriptionPlayer = find(R.id.text_view_description_detail_player)
    }

}

class DetailPlayerUI : AnkoComponent<DetailPlayerActivity> {

    override fun createView(ui: AnkoContext<DetailPlayerActivity>): View {
        return with(ui) {
            relativeLayout {

                swipeRefreshLayout {

                    scrollView {

                        linearLayout {

                            imageView {
                                id = R.id.image_view_thumb_detail_player
                                scaleType = ImageView.ScaleType.CENTER_CROP
                            }.lparams {
                                width = matchParent
                                height = dip(300)
                                bottomMargin = dip(16)
                            }

                            linearLayout {

                                textView {
                                    text = ctx.getString(R.string.label_weight)
                                    textSize = 18F
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    weight = 1F
                                }

                                textView {
                                    text = ctx.getString(R.string.label_height)
                                    textSize = 18F
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    weight = 1F
                                }

                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                orientation = LinearLayout.HORIZONTAL
                            }

                            linearLayout {

                                textView {
                                    id = R.id.text_view_weight_detail_player
                                    textSize = 24F
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    textColor = colorPrimaryDark
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    weight = 1F
                                }

                                textView {
                                    id = R.id.text_view_height_detail_player
                                    textSize = 24F
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    textColor = colorPrimaryDark
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    weight = 1F
                                }

                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                orientation = LinearLayout.HORIZONTAL
                                bottomMargin = dip(16)
                            }

                            textView {
                                id = R.id.text_view_position_detail_player
                                textColor = colorPrimaryDark
                                textSize = 24F
                            }.lparams {
                                width = wrapContent
                                height = wrapContent
                                gravity = Gravity.CENTER_HORIZONTAL
                            }

                            view {
                                backgroundColor = ContextCompat.getColor(ctx, android.R.color.darker_gray)
                            }.lparams {
                                width = matchParent
                                height = dip(1)
                                bottomMargin = dip(16)
                            }

                            textView {
                                id = R.id.text_view_description_detail_player
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                rightMargin = dip(16)
                                bottomMargin = dip(16)
                                leftMargin = dip(16)
                            }

                            lparams {
                                width = matchParent
                                height = matchParent
                                orientation = LinearLayout.VERTICAL
                            }
                        }

                        lparams {
                            width = matchParent
                            height = wrapContent
                        }
                    }

                    isEnabled = false
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }

                lparams {
                    width = matchParent
                    height = matchParent
                }
            }
        }
    }

}
