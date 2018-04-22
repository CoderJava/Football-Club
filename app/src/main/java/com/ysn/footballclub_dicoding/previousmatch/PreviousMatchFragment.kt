/*
 * Created by YSN Studio on 4/22/18 5:48 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/22/18 11:16 AM
 */

package com.ysn.footballclub_dicoding.previousmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.R.color.colorAccent
import com.ysn.footballclub_dicoding.api.ApiRepository
import com.ysn.footballclub_dicoding.detailmatch.DetailMatchActivity
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class PreviousMatchFragment : Fragment(), PreviousMatchView {

    private lateinit var presenter: PreviousMatchPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var apiRepository: ApiRepository
    private lateinit var gson: Gson
    private lateinit var adapterMatch: AdapterMatch
    private var isRefresh: Boolean = false
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
                        id = R.id.recycler_view_previous_match_fragment
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
        apiRepository = ApiRepository()
        gson = Gson()
        presenter = PreviousMatchPresenter(view = this, apiRepository = apiRepository, gson = gson)
        if (savedInstanceState != null) {
            isAlreadyLoadData = savedInstanceState.getBoolean("isAlreadyLoadData")
            if (!isAlreadyLoadData) {
                doLoadData(isRefresh = false)
            }
        } else {
            if (!isAlreadyLoadData) {
                doLoadData(isRefresh = false)
            }
        }
        swipeRefreshLayout.setOnRefreshListener {
            doLoadData(isRefresh = true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isAlreadyLoadData", isAlreadyLoadData)
    }

    private fun doLoadData(isRefresh: Boolean) {
        this.isRefresh = isRefresh
        showLoading()
        recyclerView.invisible()
        presenter.onLoadData()
    }

    private fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    override fun loadData(events: List<Event>) {
        isAlreadyLoadData = true
        hideLoading()
        if (!isRefresh) {
            adapterMatch = AdapterMatch(events = events, listenerAdapterMatch = object : AdapterMatch.ListenerAdapterMatch {
                override fun onClick(event: Event) {
                    onClickItemPreviousMatch(event = event)
                }

            })
            recyclerView.adapter = adapterMatch
            recyclerView.layoutManager = LinearLayoutManager(ctx)
            recyclerView.visible()
        } else {
            adapterMatch.refreshData(events = events as ArrayList<Event>)
            recyclerView.visible()
        }
    }

    override fun loadDataFailed(message: String) {
        isAlreadyLoadData = false
        hideLoading()
        recyclerView.invisible()
        longToast(message)
    }

    private fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onClickItemPreviousMatch(event: Event) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("event" to event)
        ctx.startActivity(intentDetailMatchActivity)
    }

}
