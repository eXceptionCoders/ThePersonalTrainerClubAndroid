package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import android.support.design.widget.TabLayout
import es.exceptioncoders.thepersonaltrainerclub.R.id.tabs_main


class TypeSelectionFragmentNavigator : BaseNavigator<TypeSelectionFragmentContract.View>(), TypeSelectionFragmentContract.Navigator<TypeSelectionFragmentContract.View> {
    override fun navigateToTrainerView() {
        var user = SharedApp.preferences.user

        user?.let {
            it.showCoachView = true
            SharedApp.preferences.user = it

            navigateToMainTab()
        }
    }

    override fun navigateToClientView() {
        var user = SharedApp.preferences.user

        user?.let {
            it.showCoachView = false
            SharedApp.preferences.user = it

            navigateToMainTab()
        }
    }

    private fun navigateToMainTab() {
        val tabLayout = (mView as TypeSelectionFragment).activity?.findViewById<TabLayout>(tabs_main)
        tabLayout?.getTabAt(0)?.select()
    }
}