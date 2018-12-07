package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import android.app.Activity
import android.content.Intent
import es.exceptioncoders.thepersonaltrainerclub.view.addLocation.AddLocationActivity
import es.exceptioncoders.thepersonaltrainerclub.view.addSport.AddSportActivity
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator

class DashboardActivityNavigator : BaseNavigator<DashboardActivityContract.View>(), DashboardActivityContract.Navigator<DashboardActivityContract.View> {
    override fun navigateToActivities() {
        val intent = Intent(mView as Activity, AddSportActivity::class.java)
        (mView as Activity).startActivity(intent)
    }

    override fun navigateToLocations() {
        val intent = Intent(mView as Activity, AddLocationActivity::class.java)
        (mView as Activity).startActivity(intent)
    }
}