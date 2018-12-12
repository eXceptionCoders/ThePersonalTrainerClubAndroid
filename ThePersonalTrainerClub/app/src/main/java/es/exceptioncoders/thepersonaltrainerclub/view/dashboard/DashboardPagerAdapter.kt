package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class DashboardPagerAdapter(fm: FragmentManager, private val ctx: Context) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return (ctx as DashboardActivity).mFragments[position]
    }

    override fun getCount(): Int {
        return (ctx as DashboardActivity).mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return (ctx as DashboardActivity).mFragmentNames[position]
    }
}
