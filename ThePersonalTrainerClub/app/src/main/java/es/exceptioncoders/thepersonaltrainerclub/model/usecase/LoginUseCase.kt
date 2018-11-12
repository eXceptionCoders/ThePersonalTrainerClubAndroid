package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProvider

interface LoginUseCase {
    fun login(model: LoginModel, completion: (Boolean, LoginProvider.LoginError?) -> Unit)
}

class LoginUseCaseImp(private val provider: LoginProvider): LoginUseCase {
    override fun login(model: LoginModel, completion: (Boolean, LoginProvider.LoginError?) -> Unit) {
        provider.login(model, completion)
    }
}