package es.exceptioncoders.thepersonaltrainerclub.view.addSport

import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface AddSportActivityContract {
    interface View : BaseContract.BaseView {
        fun showSports(sports: Array<SportModel>)
        fun showError()
    }

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun create()
        fun saveSports()
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun popBack()
    }
}