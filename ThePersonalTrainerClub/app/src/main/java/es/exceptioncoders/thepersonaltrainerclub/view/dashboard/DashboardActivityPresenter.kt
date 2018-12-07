package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class DashboardActivityPresenter(private val mNavigator: DashboardActivityContract.Navigator<DashboardActivityContract.View>) : BasePresenter<DashboardActivityContract.View>(), DashboardActivityContract.Presenter<DashboardActivityContract.View> {
    override fun onAddActivitiesTapped() {
        mNavigator.navigateToActivities()
    }

    override fun onAddLocationsTapped() {
        mNavigator.navigateToLocations()
    }

    override fun onLogout() {

    }
}