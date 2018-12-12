package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import android.support.design.widget.TabLayout
import es.exceptioncoders.thepersonaltrainerclub.R.id.tabs_main


class TypeSelectionFragmentNavigator : BaseNavigator<TypeSelectionFragmentContract.View>(), TypeSelectionFragmentContract.Navigator<TypeSelectionFragmentContract.View> {
    override fun navigateToMainTab() {
        val tabLayout = (mView as TypeSelectionFragment).activity?.findViewById<TabLayout>(tabs_main)
        tabLayout?.getTabAt(0)?.select()
    }
}