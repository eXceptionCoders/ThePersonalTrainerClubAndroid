package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.LoginRequest

interface LoginProvider {
    enum class LoginError {
        UserPasswordNotFound,
        OtherError
    }

    fun login(model: LoginModel, completion: (Boolean, LoginError?) -> Unit)
}

class LoginProviderImp: LoginProvider {
    override fun login(model: LoginModel, completion: (Boolean, LoginProvider.LoginError?) -> Unit) {
        val requestModel = LoginRequest(model.email, model.password)

        val endpoint = Endpoint(Endpoint.EndpointType.Login(requestModel))

        val ws = WebService()

        ws.load<Boolean>(endpoint) { b:Boolean?, e: WebServiceError? ->
            var error: LoginProvider.LoginError? = null
            e.let {
                when (it) {
                    WebServiceError.ForbiddenError -> LoginProvider.LoginError.UserPasswordNotFound
                    else -> LoginProvider.LoginError.OtherError
                }
            }

            completion(b ?: false, error)
        }
    }
}