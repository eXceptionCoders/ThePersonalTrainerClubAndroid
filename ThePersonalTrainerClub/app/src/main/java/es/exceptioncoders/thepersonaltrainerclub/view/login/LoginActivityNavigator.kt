package es.exceptioncoders.thepersonaltrainerclub.view.login

import android.app.Activity
import android.content.Intent
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import es.exceptioncoders.thepersonaltrainerclub.view.register.RegisterActivity

class LoginActivityNavigator : BaseNavigator<LoginActivityContract.LoginView>(), LoginActivityContract.LoginViewNavigator<LoginActivityContract.LoginView> {
    override fun navigateToRegisterView() {
        val intent = Intent(mView as Activity, RegisterActivity::class.java)
        (mView as Activity).startActivity(intent)
    }
}