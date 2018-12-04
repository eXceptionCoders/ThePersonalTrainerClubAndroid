package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface TrainerManagementActivityContract {
    interface TrainerManagementView : BaseContract.BaseView

    interface TrainerManagementViewPresenter<V: TrainerManagementView> : BaseContract.BasePresenter<V> {

    }

    interface TrainerManagementViewNavigator<V: TrainerManagementView> : BaseContract.BaseNavigator<V> {

    }
}