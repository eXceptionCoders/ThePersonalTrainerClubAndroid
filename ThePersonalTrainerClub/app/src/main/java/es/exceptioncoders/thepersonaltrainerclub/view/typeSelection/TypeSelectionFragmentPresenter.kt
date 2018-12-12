package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter
import es.exceptioncoders.thepersonaltrainerclub.view.dashboard.DashboardActivity

enum class TypeSelection {
    Trainer,
    Client
}

class TypeSelectionFragmentPresenter(private val mNavigator: TypeSelectionFragmentContract.Navigator<TypeSelectionFragmentContract.View>) : BasePresenter<TypeSelectionFragmentContract.View>(), TypeSelectionFragmentContract.Presenter<TypeSelectionFragmentContract.View> {
    override fun onTypeSelected(type: TypeSelection) {
        var user = SharedApp.preferences.user

        user?.let {
            it.showCoachView = (type == TypeSelection.Trainer)
            SharedApp.preferences.user = it
        }

        ((mView as TypeSelectionFragment).activity as DashboardActivity).updateTabTitles()
        mNavigator.navigateToMainTab()
    }
}