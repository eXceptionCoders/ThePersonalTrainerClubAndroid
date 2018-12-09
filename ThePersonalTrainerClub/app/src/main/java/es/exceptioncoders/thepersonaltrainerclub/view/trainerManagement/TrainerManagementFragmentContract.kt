package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface TrainerManagementFragmentContract {
    interface View : BaseContract.BaseView {

    }

    interface Presenter<V: View> : BaseContract.BasePresenter<V> {

    }

    interface Navigator<V: View> : BaseContract.BaseNavigator<V> {
        fun navigateToClassDetail()
    }
}