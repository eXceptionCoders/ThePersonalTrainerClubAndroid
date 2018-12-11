package es.exceptioncoders.thepersonaltrainerclub.view.addLocation

import es.exceptioncoders.thepersonaltrainerclub.R
import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.*
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.MQAddressModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LocationProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.MapQuestProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.MapQuestProviderProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.AddLocationUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.AddLocationUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter
import org.osmdroid.util.GeoPoint

class AddLocationActivityPresenter(private val mNavigator: AddLocationActivityContract.Navigator<AddLocationActivityContract.View>) : BasePresenter<AddLocationActivityContract.View>(),AddLocationActivityContract.Presenter<AddLocationActivityContract.View> {

    private val useCase: AddLocationUseCase = AddLocationUseCaseImp(LocationProviderImp())
    private val provider: MapQuestProvider = MapQuestProviderProviderImp()

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLastLocation: Location? = null
    private var showCurrentLocation = true
    private var mSelectedLocation: GeoPoint? = null

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return

            if (locationResult.locations.count() > 0) {
                mLastLocation = locationResult.locations.first()

                if (showCurrentLocation) {
                    mView?.showLocation(GeoPoint(mLastLocation!!.latitude, mLastLocation!!.longitude))
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun startLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mView as AddLocationActivity)

        mFusedLocationClient!!.flushLocations()
        mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(mView as AddLocationActivity) { task ->
                    if (task.isSuccessful && task.result != null) {
                        mLastLocation = task.result

                        if (showCurrentLocation) {
                            mView?.showLocation(GeoPoint(mLastLocation!!.latitude, mLastLocation!!.longitude))
                        }
                    } else {
                        mView?.showAlertMessage(null, R.string.add_location_user_location_error_message)
                    }
                }

        val mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval((5 * 1000).toLong())        // 5 seconds, in milliseconds
                .setFastestInterval((1 * 1000).toLong()) // 1 second, in milliseconds
        mFusedLocationClient!!.requestLocationUpdates(mLocationRequest, locationCallback, null)
    }

    override fun stopLocation() {
        mFusedLocationClient!!.removeLocationUpdates(locationCallback)
    }

    override fun getLastLocation() {
        mLastLocation?.let {
            showCurrentLocation = true
            mSelectedLocation = GeoPoint(it.latitude, it.longitude)

            mView?.showLoading()
            provider.getReverseLocation(MQAddressModel("", mSelectedLocation!!)) { response, error ->
                //TODO: Check error & null

                mView?.hideLoading()

                mView?.setLocation(mSelectedLocation!!, response!!.address)
            }
        } ?: run {
            mView?.showAlertMessage(null, R.string.add_location_no_last_location_message)
        }
    }

    override fun searchLocation(address: String) {
        mView?.showLoading()
        showCurrentLocation = false

        provider.getLocation(MQAddressModel(address, GeoPoint(0.0, 0.0))) { response, error ->
            //TODO: Check error & null

            mView?.hideLoading()

            mSelectedLocation = response!!.latlng

            mView?.setLocation(mSelectedLocation!!, response!!.address)
        }
    }

    override fun saveLocation(description: String) {
        if (description.isNullOrEmpty() || description.isBlank()) {
            mView?.showAlertMessage(null, R.string.add_location_on_save_error_message)
        } else {
            stopLocation()

            mView?.showLoading()

            mSelectedLocation?.let {
                useCase.addLocation(LocationModel("","Point", doubleArrayOf(it.latitude, it.longitude).toTypedArray(), description)) { success, error ->
                    mView?.hideLoading()

                    if (success) {
                        mNavigator.popBack()
                    }
                }
            } ?: run {
                mView?.showAlertMessage(null, R.string.add_location_on_save_error_message)
            }
        }
    }
}