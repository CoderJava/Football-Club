/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/20/18 8:19 AM
 */

package com.ysn.footballclub_dicoding.util

import kotlinx.coroutines.experimental.Unconfined
import org.junit.Assert.*
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderTest : CoroutineContextProvider() {

    override val main: CoroutineContext = Unconfined

}