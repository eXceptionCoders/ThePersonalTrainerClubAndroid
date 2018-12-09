package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class DashboardActivityPresenter(private val mNavigator: DashboardActivityContract.Navigator<DashboardActivityContract.View>) : BasePresenter<DashboardActivityContract.View>(), DashboardActivityContract.Presenter<DashboardActivityContract.View> {
    override fun onAddActivitiesTapped() {
        mNavigator.navigateToActivities()
    }

    override fun onAddLocationsTapped() {
        mNavigator.navigateToLocations()
    }

    override fun onLogoutTapped() {
        SharedApp.preferences.jwtToken = ""
        SharedApp.preferences.user = UserModel()
        mNavigator.navigateToLogin()
    }
}