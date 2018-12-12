package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.newClass.NewClassFragment
import es.exceptioncoders.thepersonaltrainerclub.view.searchClass.SearchClassFragment
import es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement.TrainerManagementFragment
import es.exceptioncoders.thepersonaltrainerclub.view.typeSelection.TypeSelectionFragment
import es.exceptioncoders.thepersonaltrainerclub.view.userSettings.UserSettingsFragment

class DashboardPagerAdapter(fm: FragmentManager, private val ctx: Context) : FragmentStatePagerAdapter(fm) {
    private val mTabTitles = ArrayList<String>()

    fun setTabTitles(newTitles: ArrayList<String>) {
        mTabTitles.clear()
        mTabTitles.addAll(newTitles)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return when {
            mTabTitles[position] == ctx.resources.getString(R.string.trainer_management_title) -> TrainerManagementFragment()
            mTabTitles[position] == ctx.resources.getString(R.string.type_selection_title) -> TypeSelectionFragment()
            mTabTitles[position] == ctx.resources.getString(R.string.new_class_title) -> NewClassFragment()
            mTabTitles[position] == ctx.resources.getString(R.string.search_class_title) -> SearchClassFragment()
            else -> UserSettingsFragment()
        }
    }

    override fun getCount(): Int {
        return mTabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTabTitles[position]
    }
}
