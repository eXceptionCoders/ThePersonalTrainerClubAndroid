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
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TrainerManagementFragment()
            1 -> TypeSelectionFragment()
            2 -> {
                return if (SharedApp.preferences.user!!.showCoachView) {
                    NewClassFragment()
                } else {
                    SearchClassFragment()
                }

            }
            else -> UserSettingsFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> ctx.resources.getString(R.string.trainer_management_title)
            1 -> ctx.resources.getString(R.string.type_selection_title)
            2 -> {
                return if (SharedApp.preferences.user!!.showCoachView) {
                    ctx.resources.getString(R.string.new_class_title)
                } else {
                    ctx.resources.getString(R.string.search_class_title)
                }
            }
            else -> ctx.resources.getString(R.string.user_settings_title)
        }
    }
}
