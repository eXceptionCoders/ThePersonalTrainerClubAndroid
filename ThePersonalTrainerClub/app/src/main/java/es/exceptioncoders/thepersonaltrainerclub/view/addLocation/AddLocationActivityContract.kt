package es.exceptioncoders.thepersonaltrainerclub.view.addLocation

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface AddLocationActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V>

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}