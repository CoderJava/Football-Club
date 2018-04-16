/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 12:45 PM
 */

package com.ysn.footballclub_dicoding.favoritematch

import android.database.sqlite.SQLiteConstraintException
import com.ysn.footballclub_dicoding.db.DatabaseOpenHelper
import com.ysn.footballclub_dicoding.db.EntityEvent
import com.ysn.footballclub_dicoding.favoritematch.adapter.AdapterMatchFavorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter constructor(private val view: FavoriteMatchView?) {

    private lateinit var adapterMatch: AdapterMatchFavorite
    private lateinit var events: List<EntityEvent>

    fun onLoadData(database: DatabaseOpenHelper) {
        events = ArrayList()
        adapterMatch = AdapterMatchFavorite(events = events, listenerAdapterMatch = object : AdapterMatchFavorite.ListenerAdapterMatch {
            override fun onClick(event: EntityEvent) {
                view?.onClickItemFavoriteMatch(event = event)
            }
        })
        try {
            database.use {
                val result = select(EntityEvent.TABLE_EVENT)
                events = result.parseList(classParser())
                adapterMatch.refreshData(events = events as java.util.ArrayList<EntityEvent>)
                view?.loadData(adapterMatch = adapterMatch)
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            view?.loadDataFailed(e.localizedMessage)
        }
    }

    fun onRefreshData(database: DatabaseOpenHelper) {
        events = ArrayList()
        try {
            database.use {
                val result = select(EntityEvent.TABLE_EVENT)
                events = result.parseList(classParser())
                adapterMatch.refreshData(events = events as java.util.ArrayList<EntityEvent>)
                view?.refreshData()
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            view?.refreshDataFailed(e.localizedMessage)
        }
    }
}