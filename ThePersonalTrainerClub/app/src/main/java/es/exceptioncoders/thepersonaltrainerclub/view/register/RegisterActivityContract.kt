package es.exceptioncoders.thepersonaltrainerclub.view.register

import es.exceptioncoders.thepersonaltrainerclub.model.model.GenderType
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract
import java.time.LocalDateTime

interface RegisterActivityContract {
    interface RegisterView : BaseContract.BaseView

    interface RegisterViewPresenter<V : RegisterView> : BaseContract.BasePresenter<V> {
        fun onRegister(name: String?, lastname: String?, gender: GenderType?, email: String?,
                       password: String?, birthday: LocalDateTime?, isTrainer: Boolean?)
    }

    interface RegisterViewNavigator<V : RegisterView> : BaseContract.BaseNavigator<V> {
        fun navigateToGeneralManagementView()
    }
}