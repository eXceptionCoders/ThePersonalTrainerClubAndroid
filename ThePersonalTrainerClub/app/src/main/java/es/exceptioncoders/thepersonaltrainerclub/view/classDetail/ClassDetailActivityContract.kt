package es.exceptioncoders.thepersonaltrainerclub.view.classDetail

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface ClassDetailActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun onBookClass(model: ClassModel)
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun popBack()
    }
}