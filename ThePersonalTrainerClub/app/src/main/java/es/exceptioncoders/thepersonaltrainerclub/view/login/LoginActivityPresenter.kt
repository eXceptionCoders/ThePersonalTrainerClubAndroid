package es.exceptioncoders.thepersonaltrainerclub.view.login

import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.LoginUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.LoginUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class LoginActivityPresenter(private val mNavigator: LoginActivityContract.LoginViewNavigator<LoginActivityContract.LoginView>) : BasePresenter<LoginActivityContract.LoginView>(), LoginActivityContract.LoginViewPresenter<LoginActivityContract.LoginView> {

    override fun onLogin(email: String?, password: String?) {
        if (email.isNullOrEmpty() || email.isNullOrBlank() || password.isNullOrEmpty() || password.isNullOrBlank()) {
            mView?.showMessage("Faltan datos")
        } else {
            val model = LoginModel(email!!, password!!)

            val useCase: LoginUseCase = LoginUseCaseImp(LoginProviderImp())
            useCase.login(model) { loggedIn, loginError ->
                print({loggedIn})
            }
        }
    }

    override fun onRegister() {
        mNavigator.navigateToRegisterView()
    }
}