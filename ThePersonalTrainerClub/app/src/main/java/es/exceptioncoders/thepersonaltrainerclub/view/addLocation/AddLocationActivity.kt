package es.exceptioncoders.thepersonaltrainerclub.view.addLocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_location.*
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class AddLocationActivity : BaseActivity(), AddLocationActivityContract.View {
    companion object {
        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

    private lateinit var mPresenter: AddLocationActivityContract.Presenter<AddLocationActivity>

    override fun bindLayout(): Int = R.layout.activity_add_location

    override fun localizeView() {
        (currentLocationButton as Button).text =  resources.getString(R.string.add_location_use_current_location_button_title)
        (searchLocationButton as Button).text = resources.getString(R.string.add_location_search_location_button_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = AddLocationActivityNavigator() as AddLocationActivityContract.Navigator<AddLocationActivityContract.View>
        mNavigator.attachView(this)

        mPresenter = AddLocationActivityPresenter(mNavigator) as AddLocationActivityContract.Presenter<AddLocationActivity>
        mPresenter.attachView(this)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.add_location_title)

        mapView.isClickable = false
        mapView.setBuiltInZoomControls(false)
        mapView.setMultiTouchControls(false)
        mapView.controller.setZoom(17.0)
        //Show Puerta del Sol by default
        mapView.controller.animateTo(GeoPoint(40.41684, -3.70341))

        currentLocationButton.setOnClickListener {
            mPresenter.getLastLocation()
        }

        searchLocationButton.setOnClickListener {
            if (searchLocationText.text.isNullOrEmpty() || searchLocationText.text.isBlank()) {
                showAlertMessage(null, R.string.add_location_on_search_error_message)
            } else {
                mPresenter.searchLocation(searchLocationText.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()

        if (!checkPermissions()) {
            requestPermissions()
        } else {
            mPresenter.startLocation()
        }
    }

    override fun onPause() {
        super.onPause()

        mPresenter.stopLocation()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        return if (id == R.id.action_save) {
            mPresenter.saveLocation(descriptionText.text.toString())
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun showLocation(center: GeoPoint) {
        mapView.controller.animateTo(center)
        addMarker(center)
    }

    override fun setLocation(center: GeoPoint, address: String) {
        mapView.controller.animateTo(center)
        addMarker(center)
        locationText.text = address
    }

    private fun addMarker(center: GeoPoint) {
        var marker = Marker(mapView)
        marker.position = center
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

        mapView.overlays.clear()
        mapView.overlays.add(marker)

        mapView.invalidate()
    }

    private fun checkPermissions(): Boolean {
        val coarsePermissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        val internetPermissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
        val wifiPermissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_WIFI_STATE)
        val networkPermissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE)
        val finePermissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        val storagePermissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)

        return (coarsePermissionState == PackageManager.PERMISSION_GRANTED) &&
                (internetPermissionState == PackageManager.PERMISSION_GRANTED) &&
                (wifiPermissionState == PackageManager.PERMISSION_GRANTED) &&
                (networkPermissionState == PackageManager.PERMISSION_GRANTED) &&
                (finePermissionState == PackageManager.PERMISSION_GRANTED) &&
                (storagePermissionState == PackageManager.PERMISSION_GRANTED)
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this@AddLocationActivity,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    private fun requestPermissions() {
        startLocationPermissionRequest()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size <= 0) {
                // User interaction was cancelled.
                // TODO: PopBack?
            } else if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                    (grantResults[1] == PackageManager.PERMISSION_GRANTED) &&
                    (grantResults[2] == PackageManager.PERMISSION_GRANTED) &&
                    (grantResults[3] == PackageManager.PERMISSION_GRANTED) &&
                    (grantResults[4] == PackageManager.PERMISSION_GRANTED) &&
                    (grantResults[5] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted.
                mPresenter.startLocation()
            } else {
                // TODO: Show message?
                // Permission denied.
            }
        }
    }
}

