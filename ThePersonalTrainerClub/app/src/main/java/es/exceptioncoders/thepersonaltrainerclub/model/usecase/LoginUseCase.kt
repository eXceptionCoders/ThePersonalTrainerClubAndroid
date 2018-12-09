package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LoginProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface LoginUseCase {
    fun login(model: LoginModel, completion: (Boolean, LoginProvider.LoginError?) -> Unit)
}

class LoginUseCaseImp(private val provider: LoginProvider, private val userProvider: UserProvider): LoginUseCase {
    override fun login(model: LoginModel, completion: (Boolean, LoginProvider.LoginError?) -> Unit) {
        provider.login(model) { success, error ->
            if (success) {
                userProvider.getUser { user, error ->
                    user?.let {
                        SharedApp.preferences.user = user

                        completion(true, null)
                    }
                }
            } else {
                completion(false, error)
            }
        }
    }
}