/*
 * Created by YSN Studio on 4/12/18 4:17 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/11/18 6:59 PM
 */

package com.ysn.footballclub_dicoding.nextmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.footballclub_dicoding.BuildConfig
import com.ysn.footballclub_dicoding.R
import com.ysn.footballclub_dicoding.api.Endpoints
import com.ysn.footballclub_dicoding.detailmatch.DetailMatchActivity
import com.ysn.footballclub_dicoding.detailmatch.adapter.AdapterMatch
import com.ysn.footballclub_dicoding.model.Event
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var endpoints: Endpoints
    private var isAlreadyLoadData: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = UI {
            linearLayout {
                swipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(
                            R.color.colorAccent,
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
        super.onViewCreated(view, savedInstanceState)
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        endpoints = retrofit.create(Endpoints::class.java)
        presenter = NextMatchPresenter(view = this, endpoints = endpoints)
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
        presenter.onRefreshData()
    }

    private fun doLoadData() {
        showLoading()
        recyclerView.invisible()
        presenter.onLoadData()
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

    override fun loadData(adapterMatch: AdapterMatch) {
        isAlreadyLoadData = true
        hideLoading()
        recyclerView.adapter = adapterMatch
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.visible()
    }

    override fun loadDataFailed(message: String) {
        isAlreadyLoadData = false
        hideLoading()
        recyclerView.invisible()
        longToast(message)
    }

    override fun refreshData() {
        isAlreadyLoadData = true
        hideLoading()
        recyclerView.visible()
    }

    override fun refreshDataFailed(message: String) {
        isAlreadyLoadData = false
        hideLoading()
        recyclerView.invisible()
        longToast(message)
    }

    override fun onClickItemNextMatch(event: Event) {
        val intentDetailMatchActivity = ctx.intentFor<DetailMatchActivity>("event" to event)
        ctx.startActivity(intentDetailMatchActivity)
    }

}
