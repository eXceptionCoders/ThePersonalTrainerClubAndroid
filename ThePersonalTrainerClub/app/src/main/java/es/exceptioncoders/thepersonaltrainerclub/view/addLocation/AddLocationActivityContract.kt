package es.exceptioncoders.thepersonaltrainerclub.view.addLocation

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract
import org.osmdroid.util.GeoPoint

interface AddLocationActivityContract {
    interface View : BaseContract.BaseView {
        fun showLocation(center: GeoPoint)
        fun setLocation(center: GeoPoint, address: String)
    }

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun startLocation()
        fun stopLocation()
        fun getLastLocation()
        fun searchLocation(address: String)
        fun saveLocation(description: String)
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun popBack()
    }
}