package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface SearchClassResultActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V>

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}