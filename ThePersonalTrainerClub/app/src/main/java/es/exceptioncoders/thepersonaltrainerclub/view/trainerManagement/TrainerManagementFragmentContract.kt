package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface TrainerManagementFragmentContract {
    interface View : BaseContract.BaseView {
        fun refreshView()
    }

    interface Presenter<V: View> : BaseContract.BasePresenter<V> {
        fun onClassClicked(position: Int)
        fun onDeleteBookClicked(model: ClassModel)
    }

    interface Navigator<V: View> : BaseContract.BaseNavigator<V> {
        fun navigateToClassDetail( model: ClassModel )
    }
}