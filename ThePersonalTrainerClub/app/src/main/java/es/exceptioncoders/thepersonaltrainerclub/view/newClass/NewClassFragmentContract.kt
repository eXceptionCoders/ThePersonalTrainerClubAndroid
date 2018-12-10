package es.exceptioncoders.thepersonaltrainerclub.view.newClass

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface NewClassFragmentContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V>

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}