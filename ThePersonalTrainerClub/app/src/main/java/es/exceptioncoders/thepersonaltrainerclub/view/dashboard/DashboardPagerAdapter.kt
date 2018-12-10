package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement.TrainerManagementFragment
import es.exceptioncoders.thepersonaltrainerclub.view.typeSelection.TypeSelectionFragment

class DashboardPagerAdapter(fm: FragmentManager, private val ctx: Context) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TrainerManagementFragment()
            1 -> TypeSelectionFragment()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> ctx.resources.getString(R.string.trainer_management_title)
            1 -> ctx.resources.getString(R.string.type_selection_title)
            else -> "TODO: RELLENAR"
        }
    }
}