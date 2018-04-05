/*
 * Created by YSN Studio on 4/5/18 9:47 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/5/18 9:42 AM
 */

package com.ysn.footballclub_dicoding

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ysn.footballclub_dicoding.model.Football
import kotlinx.android.synthetic.main.activity_detail_club.*

class DetailClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_club)

        val data = intent.getSerializableExtra("data") as Football
        Glide.with(this)
                .load(data.image)
                .into(image_view_club_activity_detail_club)
        text_view_club_name_activity_detail_club.text = data.name
        text_view_club_detaiL_activity_detail_club.text = data.detail
    }
}
