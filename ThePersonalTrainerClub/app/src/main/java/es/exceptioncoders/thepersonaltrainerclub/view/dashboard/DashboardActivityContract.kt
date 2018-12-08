package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface DashboardActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun onAddActivitiesTapped()
        fun onAddLocationsTapped()
        fun onLogout()
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun navigateToActivities()
        fun navigateToLocations()
    }
}