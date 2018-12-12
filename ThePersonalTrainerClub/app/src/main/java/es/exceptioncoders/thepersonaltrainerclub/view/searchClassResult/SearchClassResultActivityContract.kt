package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface SearchClassResultActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun onClassTapped(item: ClassModel)
        fun onBookClassTapped(item: ClassModel)
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun navigateToClassDetail(model: ClassModel)
        fun popBack()
    }
}