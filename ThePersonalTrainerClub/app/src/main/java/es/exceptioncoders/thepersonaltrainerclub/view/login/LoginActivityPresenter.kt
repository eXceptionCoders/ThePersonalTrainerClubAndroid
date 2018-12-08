package es.exceptioncoders.thepersonaltrainerclub.view.login

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.LoginUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.LoginUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class LoginActivityPresenter(private val mNavigator: LoginActivityContract.Navigator<LoginActivityContract.View>) : BasePresenter<LoginActivityContract.View>(), LoginActivityContract.Presenter<LoginActivityContract.View> {

    val useCase: LoginUseCase = LoginUseCaseImp(LoginProviderImp(), UserProviderImp())

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
                    mNavigator.navigateToDashboardActivity()
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