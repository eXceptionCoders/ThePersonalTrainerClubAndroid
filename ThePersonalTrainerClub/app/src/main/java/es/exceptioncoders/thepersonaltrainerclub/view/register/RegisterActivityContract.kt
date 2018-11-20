package es.exceptioncoders.thepersonaltrainerclub.view.register

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface RegisterActivityContract {
    interface RegisterView : BaseContract.BaseView

    interface RegisterViewPresenter<V : RegisterView> : BaseContract.BasePresenter<V> {
        fun onRegister(
                name: String?,
                surname: String?,
                email: String?,
                password: String?,
                isTrainer: Boolean?
        )
    }

    interface RegisterViewNavigator<V : RegisterView> : BaseContract.BaseNavigator<V> {
        fun navigateToGeneralManagementView()
    }
}