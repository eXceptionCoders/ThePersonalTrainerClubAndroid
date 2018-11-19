package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.LoginRequest

interface LoginProvider {
    enum class LoginError {
        UserPasswordNotFound,
        IncorrectEntry,
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
            var loggedIn = false
            e?.let {
                error = when (it) {
                    WebServiceError.Unauthorized -> LoginProvider.LoginError.UserPasswordNotFound
                    WebServiceError.UnprocessableEntity -> LoginProvider.LoginError.IncorrectEntry
                    else -> LoginProvider.LoginError.OtherError
                }
            } ?: kotlin.run {
                loggedIn = true
            }

            completion(loggedIn, error)
        }
    }
}