package es.exceptioncoders.thepersonaltrainerclub.model.provider

import android.content.Context
import es.exceptioncoders.thepersonaltrainerclub.model.model.LoginModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.LoginRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.LoginResponse
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface LoginProvider {
    enum class LoginError {
        UserPasswordNotFound,
        IncorrectEntry,
        OtherError
    }

    fun login(model: LoginModel, completion: (Boolean, LoginError?) -> Unit)
}

class LoginProviderImp(private val mContext: Context?): LoginProvider {

    override fun login(model: LoginModel, completion: (Boolean, LoginProvider.LoginError?) -> Unit) {
        val requestModel = LoginRequest(model.email, model.password)

        val endpoint = Endpoint(Endpoint.EndpointType.Login(requestModel))

        val ws = WebService()

        ws.load<LoginResponse>(endpoint) { response: LoginResponse?, e: WebServiceError? ->
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
                SharedApp.preferences.jwtToken = response!!.data!!.token
            }

            completion(loggedIn, error)
        }
    }
}