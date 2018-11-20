package es.exceptioncoders.thepersonaltrainerclub.view.register

import es.exceptioncoders.thepersonaltrainerclub.model.model.RegisterModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.RegisterUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.RegisterUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class RegisterActivityPresenter(private val mNavigator: RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView>) : BasePresenter<RegisterActivityContract.RegisterView>(), RegisterActivityContract.RegisterViewPresenter<RegisterActivityContract.RegisterView> {

    val useCase: RegisterUseCase = RegisterUseCaseImp(RegisterProviderImp())

    override fun onRegister(name: String?, surname: String?, email: String?, password: String?, isTrainer: Boolean?) {
        if (isValidFormData(name, surname, email, password, isTrainer)) {
            //TODO: Utilizar R.string.XXX
            //mView?.showAlertMessage(null, "Faltan datos")
            return
        }

        mView?.showLoading()

        val model = RegisterModel(name!!, surname!!, email!!, password!!, isTrainer!!)

        useCase.register(model) { registered, RegisterError ->
            mView?.hideLoading()

            RegisterError?.let {
                when (it) {
                    //TODO: Utilizar R.string.XXX
                    //RegisterProvider.RegisterError.DuplicateError -> mView?.showAlertMessage(null, "Ya existe un usuario con ese email")
                    //RegisterProvider.RegisterError.IncorrectEntry -> mView?.showAlertMessage(null, "El email está mal escrito")
                    //RegisterProvider.RegisterError.OtherError -> mView?.showAlertMessage(null, "Ha ocurrido un error durante el Register")
                }
                return@register
            }

            if (registered) {
                //TODO: Utilizar R.string.XXX
                //mView?.showAlertMessage(null, "OK")
            } else {
                //TODO: Utilizar R.string.XXX
                //mView?.showAlertMessage(null, "Ha ocurrido un error durante el Register")
            }
        }
    }

    private fun isValidFormData(name: String?, surname: String?, email: String?, password: String?, isTrainer: Boolean?): Boolean {
        return !(name.isNullOrEmpty() || name.isNullOrBlank() ||
                surname.isNullOrEmpty() || surname.isNullOrBlank() ||
                email.isNullOrEmpty() || email.isNullOrBlank() ||
                password.isNullOrEmpty() || password.isNullOrBlank() ||
                isTrainer == null)
    }
}