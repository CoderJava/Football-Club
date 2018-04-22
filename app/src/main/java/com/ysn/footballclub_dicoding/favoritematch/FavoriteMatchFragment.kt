/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:39 AM
 */

package com.ysn.footballclub_dicoding.favoritematch


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.R.color.colorAccent
import com.ysn.footballclub_dicoding.db.DatabaseOpenHelper
import com.ysn.footballclub_dicoding.db.EntityEvent
import com.ysn.footballclub_dicoding.detailmatch.DetailMatchActivity
import com.ysn.footballclub_dicoding.favoritematch.adapter.AdapterMatchFavorite
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment(), AnkoLogger {

    private lateinit var database: DatabaseOpenHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapterMatch: AdapterMatchFavorite
    private lateinit var events: List<EntityEvent>
    private var isAlreadyLoadData: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = UI {
            linearLayout {
                swipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(
                            colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light
                    )

                    recyclerView = recyclerView {
                        id = R.id.recycler_view_favorite_match_fragment
                        backgroundColor = android.R.color.darker_gray
                        topPadding = dip(16)
                        rightPadding = dip(16)
                        bottomPadding = dip(16)
                        leftPadding = dip(16)
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                        addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
                    }
                }
            }
        }.view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = DatabaseOpenHelper.getInstance(ctx)
        if (savedInstanceState != null) {
            isAlreadyLoadData = savedInstanceState.getBoolean("isAlreadyLoadData")
            if (!isAlreadyLoadData) {
                doLoadData()
            }
        } else {
            if (!isAlreadyLoadData) {
                doLoadData()
            }
        }
        swipeRefreshLayout.setOnRefreshListener {
            doRefreshData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isAlreadyLoadData", isAlreadyLoadData)
    }

    private fun doRefreshData() {
        recyclerView.invisible()
        events = ArrayList()
        try {
            database.use {
                val result = select(EntityEvent.TABLE_EVENT)
                events = result.parseList(classParser())
                adapterMatch.refreshData(events = events as java.util.ArrayList<EntityEvent>)
                isAlreadyLoadData = true
                hideLoading()
                recyclerView.visible()
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            isAlreadyLoadData = false
            hideLoading()
            recyclerView.invisible()
            longToast(e.localizedMessage)
        }
    }

    private fun doLoadData() {
        showLoading()
        recyclerView.invisible()
        events = ArrayList()
        adapterMatch = AdapterMatchFavorite(events = events, listenerAdapterMatch = object : AdapterMatchFavorite.ListenerAdapterMatch {
            override fun onClick(event: EntityEvent) {
                onClickItemFavoriteMatch(event = event)
            }
        })
        try {
            database.use {
                val result = select(EntityEvent.TABLE_EVENT)
                events = result.parseList(classParser())
                adapterMatch.refreshData(events = events as java.util.ArrayList<EntityEvent>)
                isAlreadyLoadData = true
                hideLoading()
                recyclerView.adapter = adapterMatch
                recyclerView.layoutManager = LinearLayoutManager(ctx)
                recyclerView.visible()
            }
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
            isAlreadyLoadData = false
            hideLoading()
            recyclerView.invisible()
            longToast(e.localizedMessage)
        }
    }

    private fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun onClickItemFavoriteMatch(event: EntityEvent) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("entityEvent" to event)
        ctx.startActivity(intentDetailMatchActivity)
    }

}
