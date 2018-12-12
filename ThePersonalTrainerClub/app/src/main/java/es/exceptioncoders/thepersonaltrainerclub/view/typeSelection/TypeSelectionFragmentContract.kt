package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface TypeSelectionFragmentContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun onTypeSelected(type: TypeSelection)
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun navigateToMainTab()
    }
}