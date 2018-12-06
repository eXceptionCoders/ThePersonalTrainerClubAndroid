package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement.TrainerManagementFragment
import es.exceptioncoders.thepersonaltrainerclub.view.typeSelection.TypeSelectionFragment

class DashboardPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
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
            0 -> "ABCJorge"
            1 -> "STT"
            else -> {
                return "Third Tab"
            }
        }
    }
}