package es.exceptioncoders.thepersonaltrainerclub.view.searchClass

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface SearchClassFragmentContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V>

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}