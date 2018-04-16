/*
 * Created by YSN Studio on 4/16/18 9:39 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/15/18 1:01 PM
 */

package com.ysn.footballclub_dicoding.favoritematch


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
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment(), FavoriteMatchView, AnkoLogger {

    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var database: DatabaseOpenHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
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
        presenter = FavoriteMatchPresenter(this)
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
        presenter.onRefreshData(database = database)
    }

    private fun doLoadData() {
        showLoading()
        recyclerView.invisible()
        presenter.onLoadData(database = database)
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

    override fun loadData(adapterMatch: AdapterMatchFavorite) {
        info { "adapterMatch count: ${adapterMatch.itemCount}" }
        isAlreadyLoadData = true
        hideLoading()
        recyclerView.adapter = adapterMatch
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.visible()
    }

    override fun loadDataFailed(localizedMessage: String) {
        isAlreadyLoadData = false
        hideLoading()
        recyclerView.invisible()
        longToast(localizedMessage)
    }

    override fun onClickItemFavoriteMatch(event: EntityEvent) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("entityEvent" to event)
        ctx.startActivity(intentDetailMatchActivity)
    }

    override fun refreshData() {
        isAlreadyLoadData = true
        hideLoading()
        recyclerView.visible()
    }

    override fun refreshDataFailed(localizedMessage: String) {
        isAlreadyLoadData = false
        hideLoading()
        recyclerView.invisible()
        longToast(localizedMessage)
    }
}
