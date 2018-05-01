/*
 * Created by YSN Studio on 5/1/18 11:50 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/1/18 6:05 PM
 */

package com.ysn.footballclub_dicoding.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseOpenHelper constructor(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

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
        createTableEvent(db)
        createTableTeam(db)
    }

    private fun createTableEvent(db: SQLiteDatabase) {
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

    private fun createTableTeam(db: SQLiteDatabase) {
        db.createTable(EntityTeam.TABLE_TEAM, true,
                EntityTeam.ID_TEAM to TEXT + PRIMARY_KEY,
                EntityTeam.STR_TEAM to TEXT,
                EntityTeam.STR_ALTERNATE to TEXT,
                EntityTeam.INT_FORMED_YEAR to TEXT,
                EntityTeam.STR_STADIUM to TEXT,
                EntityTeam.STR_DESCRIPTION_EN to TEXT,
                EntityTeam.STR_COUNTRY to TEXT,
                EntityTeam.STR_TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropIndex(EntityEvent.TABLE_EVENT, true)
        db.dropIndex(EntityTeam.TABLE_TEAM, true)
    }

}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)