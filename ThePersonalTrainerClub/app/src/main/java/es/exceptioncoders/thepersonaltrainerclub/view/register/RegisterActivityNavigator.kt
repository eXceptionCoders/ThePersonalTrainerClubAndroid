package es.exceptioncoders.thepersonaltrainerclub.view.register

import android.app.Activity
import android.content.Intent
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator

class RegisterActivityNavigator : BaseNavigator<RegisterActivityContract.RegisterView>(), RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView> {
    override fun navigateToGeneralManagementView() {
        val intent = Intent(mView as Activity, RegisterActivity::class.java)
        (mView as Activity).startActivity(intent)
    }
}