package es.exceptioncoders.thepersonaltrainerclub.view.login

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
            mView?.showAlertMessage(null, "Faltan datos")
        } else {
            mView?.showLoading()

            val model = LoginModel(email!!, password!!)

            useCase.login(model) { loggedIn, loginError ->
                mView?.hideLoading()

                loginError?.let {
                    when (it) {
                        LoginProvider.LoginError.UserPasswordNotFound -> mView?.showAlertMessage(null, "Usuario no encontrado o password incorrecto")
                        LoginProvider.LoginError.IncorrectEntry -> mView?.showAlertMessage(null, "El email está mal escrito")
                        LoginProvider.LoginError.OtherError -> mView?.showAlertMessage(null, "Ha ocurrido un error durante el login")
                    }
                    return@login
                }

                if (loggedIn) {
                    mView?.showAlertMessage(null, "OK")
                } else {
                    mView?.showAlertMessage(null, "Ha ocurrido un error durante el login")
                }
            }
        }
    }

    override fun onRegister() {
        mNavigator.navigateToRegisterView()
    }
}