package ru.dmitry.belyaev.app.weather.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import ru.dmitry.belyaev.app.weather.R

abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_base, container, false)
    }

    open fun onRefresh() {
        swipeRefresh.isRefreshing = false
    }


}