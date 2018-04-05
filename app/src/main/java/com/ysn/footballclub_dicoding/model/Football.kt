/*
 * Created by YSN Studio on 4/5/18 9:47 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/5/18 9:42 AM
 */

package com.ysn.footballclub_dicoding.model

import java.io.Serializable

data class Football(
        val name: String,
        val image: Int,
        val detail: String
) : Serializable
