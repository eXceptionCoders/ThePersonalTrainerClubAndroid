package es.exceptioncoders.thepersonaltrainerclub.view.register

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.GenderType
import es.exceptioncoders.thepersonaltrainerclub.model.model.RegisterModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.RegisterUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.RegisterUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter
import java.time.LocalDateTime

class RegisterActivityPresenter(private val mNavigator: RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView>) : BasePresenter<RegisterActivityContract.RegisterView>(), RegisterActivityContract.RegisterViewPresenter<RegisterActivityContract.RegisterView> {

    val useCase: RegisterUseCase = RegisterUseCaseImp(RegisterProviderImp())

    override fun onRegister(name: String?, lastname: String?, gender: GenderType?, email: String?,
                            password: String?, isTrainer: Boolean?) {

        if (isInvalidFormData(name, lastname, gender, email, password, isTrainer)) {
            mView?.showAlertMessage(null, R.string.register_error_empty_field)
            return
        }

        mView?.showLoading()

        val model = RegisterModel(name!!, lastname!!, gender!!, email!!, password!!, isTrainer!!)

        useCase.register(model) { registered, RegisterError ->
            mView?.hideLoading()

            RegisterError?.let {
                when (it) {
                    RegisterProvider.RegisterError.DuplicateError -> mView?.showAlertMessage(null, R.string.register_error_duplicate_user)
                    RegisterProvider.RegisterError.IncorrectEntry -> mView?.showAlertMessage(null, R.string.register_error_wrong_user_data)
                    RegisterProvider.RegisterError.OtherError -> mView?.showAlertMessage(null, R.string.register_error_default)
                }
                return@register
            }

            if (registered) {
                mView?.showAlertMessage(null, R.string.register_ok)
            } else {
                mView?.showAlertMessage(null,  R.string.register_error_default)
            }
        }
    }

    private fun isInvalidFormData(name: String?, lastname: String?, gender: GenderType?,
                                  email: String?, password: String?,
                                  isTrainer: Boolean?): Boolean {

        return (name.isNullOrEmpty() || name.isNullOrBlank() ||
                lastname.isNullOrEmpty() || lastname.isNullOrBlank() ||
                gender == null ||
                email.isNullOrEmpty() || email.isNullOrBlank() ||
                password.isNullOrEmpty() || password.isNullOrBlank() ||
                isTrainer == null)
    }
}