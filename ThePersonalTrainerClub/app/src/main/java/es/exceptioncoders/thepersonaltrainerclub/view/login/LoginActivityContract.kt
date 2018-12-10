package es.exceptioncoders.thepersonaltrainerclub.view.login

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface LoginActivityContract {
    interface View : BaseContract.BaseView

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun onCreate()
        fun onLogin(email: String?, password: String?)
        fun onRegister()
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V> {
        fun navigateToRegisterView()
        fun navigateToDashboardActivity()
    }
}