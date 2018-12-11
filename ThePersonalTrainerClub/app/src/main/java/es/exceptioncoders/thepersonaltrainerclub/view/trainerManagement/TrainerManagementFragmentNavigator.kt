package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import android.app.Activity
import android.content.Intent
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import es.exceptioncoders.thepersonaltrainerclub.view.classDetail.ClassDetailActivity
import es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement.TrainerManagementFragmentContract


class TrainerManagementFragmentNavigator : BaseNavigator<TrainerManagementFragmentContract.View>(), TrainerManagementFragmentContract.Navigator<TrainerManagementFragmentContract.View> {
    override fun navigateToClassDetail(model: ClassModel) {
        val intent = Intent(mView as Activity, ClassDetailActivity::class.java)
        (mView as Activity).startActivity(intent)
    }
}
