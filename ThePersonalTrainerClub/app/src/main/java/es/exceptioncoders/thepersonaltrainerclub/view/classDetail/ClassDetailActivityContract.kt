package es.exceptioncoders.thepersonaltrainerclub.view.classDetail

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface ClassDetailActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V>

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}