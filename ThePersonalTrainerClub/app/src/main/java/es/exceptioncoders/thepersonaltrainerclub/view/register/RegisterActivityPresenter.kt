package es.exceptioncoders.thepersonaltrainerclub.view.register

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.RegisterModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.RegisterUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.RegisterUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class RegisterActivityPresenter(private val mNavigator: RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView>) : BasePresenter<RegisterActivityContract.RegisterView>(), RegisterActivityContract.RegisterViewPresenter<RegisterActivityContract.RegisterView> {

    val useCase: RegisterUseCase = RegisterUseCaseImp(RegisterProviderImp())

    override fun onRegister(name: String?, surname: String?, email: String?, password: String?, isTrainer: Boolean?) {
        if (isInvalidFormData(name, surname, email, password, isTrainer)) {
            mView?.showAlertMessage(null, R.string.login_error_empty_field)
            return
        }

        mView?.showLoading()

        val model = RegisterModel(name!!, surname!!, email!!, password!!, isTrainer!!)

        useCase.register(model) { registered, RegisterError ->
            mView?.hideLoading()

            RegisterError?.let {
                when (it) {
                    RegisterProvider.RegisterError.DuplicateError -> mView?.showAlertMessage(null, R.string.register_error_duplicate_user)
                    RegisterProvider.RegisterError.IncorrectEntry -> mView?.showAlertMessage(null, R.string.error_invalid_email)
                    RegisterProvider.RegisterError.OtherError -> mView?.showAlertMessage(null, R.string.register_error_default)
                }
                return@register
            }

            if (registered) {
                mView?.showAlertMessage(null, R.string.ok)
            } else {
                mView?.showAlertMessage(null,  R.string.register_error_default)
            }
        }
    }

    private fun isInvalidFormData(name: String?, surname: String?, email: String?, password: String?, isTrainer: Boolean?): Boolean {
        return (name.isNullOrEmpty() || name.isNullOrBlank() ||
                surname.isNullOrEmpty() || surname.isNullOrBlank() ||
                email.isNullOrEmpty() || email.isNullOrBlank() ||
                password.isNullOrEmpty() || password.isNullOrBlank() ||
                isTrainer == null)
    }
}