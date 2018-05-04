/*
 * Created by YSN Studio on 5/4/18 5:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/3/18 10:05 PM
 */

package com.ysn.footballclubdicoding.util

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutineContextProvider {

    open val main: CoroutineContext by lazy { UI }

}