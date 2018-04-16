/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 1:08 PM
 */

package com.ysn.footballclub_dicoding.db

import android.content.Context
import android.content.Entity
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx = ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(EntityEvent.TABLE_EVENT, true,
                EntityEvent.ID_EVENT to TEXT + PRIMARY_KEY,
                EntityEvent.DATE_EVENT to TEXT,
                EntityEvent.ID_HOME_TEAM to TEXT,
                EntityEvent.ID_AWAY_TEAM to TEXT,
                EntityEvent.INT_HOME_SCORE to TEXT,
                EntityEvent.INT_AWAY_SCORE to TEXT,
                EntityEvent.STR_HOME_TEAM to TEXT,
                EntityEvent.STR_AWAY_TEAM to TEXT,
                EntityEvent.STR_HOME_FORMATION to TEXT,
                EntityEvent.STR_AWAY_FORMATION to TEXT,
                EntityEvent.STR_HOME_GOAL_DETAILS to TEXT,
                EntityEvent.STR_AWAY_GOAL_DETAILS to TEXT,
                EntityEvent.INT_HOME_SHOTS to TEXT,
                EntityEvent.INT_AWAY_SHOTS to TEXT,
                EntityEvent.STR_HOME_LINEUP_GOAL_KEEPER to TEXT,
                EntityEvent.STR_AWAY_LINEUP_GOAL_KEEPER to TEXT,
                EntityEvent.STR_HOME_LINEUP_DEFENSE to TEXT,
                EntityEvent.STR_AWAY_LINEUP_DEFENSE to TEXT,
                EntityEvent.STR_HOME_LINEUP_MIDFIELD to TEXT,
                EntityEvent.STR_AWAY_LINEUP_MIDFIELD to TEXT,
                EntityEvent.STR_HOME_LINEUP_FORWARD to TEXT,
                EntityEvent.STR_AWAY_LINEUP_FORWARD to TEXT,
                EntityEvent.STR_HOME_LINEUP_SUBSTITUTES to TEXT,
                EntityEvent.STR_AWAY_LINEUP_SUBSTITUTES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropIndex(EntityEvent.TABLE_EVENT, true)
    }

}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)