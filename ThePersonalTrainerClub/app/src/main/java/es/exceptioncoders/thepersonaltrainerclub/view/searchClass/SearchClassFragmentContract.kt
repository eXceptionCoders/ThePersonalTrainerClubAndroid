package es.exceptioncoders.thepersonaltrainerclub.view.searchClass

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface SearchClassFragmentContract {
    interface View : BaseContract.BaseView {
        fun setUser(user: UserModel)
        fun resetInputs()
    }

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun fetchUser()
        fun onSearch(sport: SportModel, location: LocationModel, distance: Int, maxPrice: Int)
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}