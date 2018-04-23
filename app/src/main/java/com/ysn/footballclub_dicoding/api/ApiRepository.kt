/*
 * Created by YSN Studio on 4/24/18 1:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/24/18 12:52 AM
 */

package com.ysn.footballclub_dicoding.api

import java.net.URL

class ApiRepository {

    fun doRequest(url: String): String = URL(url).readText()

}