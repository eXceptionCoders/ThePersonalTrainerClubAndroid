package es.exceptioncoders.thepersonaltrainerclub.view.login

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface LoginActivityContract {
    interface LoginView : BaseContract.BaseView

    interface LoginViewPresenter<V : LoginView> : BaseContract.BasePresenter<V> {
        fun onLogin(email: String?, password: String?)
        fun onRegister()
    }

    interface LoginViewNavigator<V : LoginView> : BaseContract.BaseNavigator<V> {
        fun navigateToRegisterView()
    }
}