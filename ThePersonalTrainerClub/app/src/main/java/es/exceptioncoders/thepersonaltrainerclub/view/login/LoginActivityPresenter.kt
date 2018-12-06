package es.exceptioncoders.thepersonaltrainerclub.view.login

import android.content.Context
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.LoginUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.LoginUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class LoginActivityPresenter(private val mNavigator: LoginActivityContract.LoginViewNavigator<LoginActivityContract.LoginView>) : BasePresenter<LoginActivityContract.LoginView>(), LoginActivityContract.LoginViewPresenter<LoginActivityContract.LoginView> {

    val useCase: LoginUseCase = LoginUseCaseImp(LoginProviderImp())

    override fun onLogin(email: String?, password: String?) {
        if (email.isNullOrEmpty() || email.isNullOrBlank() || password.isNullOrEmpty() || password.isNullOrBlank()) {
            mView?.showAlertMessage(R.string.login_error_title, R.string.login_error_empty_field)
        } else {
            mView?.showLoading()

            val model = LoginModel(email!!, password!!)

            useCase.login(model) { loggedIn, loginError ->
                mView?.hideLoading()

                loginError?.let {
                    when (it) {
                        LoginProvider.LoginError.UserPasswordNotFound -> mView?.showAlertMessage(null, R.string.login_error_wrong_user)
                        LoginProvider.LoginError.IncorrectEntry -> mView?.showAlertMessage(null, R.string.login_error_invalid_email)
                        LoginProvider.LoginError.OtherError -> mView?.showAlertMessage(null, R.string.login_error_default)
                    }
                    return@login
                }

                if (loggedIn) {
                    mView?.showAlertMessage(null, android.R.string.ok)
                    // mNavigator.navigateToTrainerManagementView()
                } else {
                    mView?.showAlertMessage(null, R.string.login_error_default)
                }
            }
        }
    }

    override fun onRegister() {
        mNavigator.navigateToRegisterView()
    }
}