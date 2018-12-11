package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class TrainerManagementFragmentPresenter(private val mNavigator: TrainerManagementFragmentContract.Navigator<TrainerManagementFragmentContract.View>) : BasePresenter<TrainerManagementFragmentContract.View>(), TrainerManagementFragmentContract.Presenter<TrainerManagementFragmentContract.View> {
    override fun onClassClicked(position: Int) {
        SharedApp.preferences.user?.let {
            this.mNavigator.navigateToClassDetail(it.activeBookings[position])
        }
    }
}