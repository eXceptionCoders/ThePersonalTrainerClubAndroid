package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import android.app.Activity
import android.support.v4.app.Fragment
import android.content.Intent
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import es.exceptioncoders.thepersonaltrainerclub.view.classDetail.ClassDetailActivity
import es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement.TrainerManagementFragmentContract


class TrainerManagementFragmentNavigator : BaseNavigator<TrainerManagementFragmentContract.View>(), TrainerManagementFragmentContract.Navigator<TrainerManagementFragmentContract.View> {
    override fun navigateToClassDetail(model: ClassModel) {
        val intent = Intent((mView as Fragment).activity, ClassDetailActivity::class.java)
        (mView as Fragment).activity!!.startActivity(intent)
    }
}
