package es.exceptioncoders.thepersonaltrainerclub.view.newClass

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface NewClassFragmentContract {
    interface View : BaseContract.BaseView {
        fun setUser(user: UserModel)
        fun resetInputs()
    }

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun fetchUser()
        fun onCreate(sport: SportModel, location: LocationModel, description: String, price: Double, quota: Int)
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}