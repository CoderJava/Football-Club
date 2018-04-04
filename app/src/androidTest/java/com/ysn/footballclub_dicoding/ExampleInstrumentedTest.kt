/*
 * Created by YSN Studio on 4/4/18 9:27 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/4/18 9:26 AM
 */

package com.ysn.footballclub_dicoding

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.ysn.footballclub_dicoding", appContext.packageName)
    }
}
