package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserResponse

interface UserProvider {
    enum class UserError {
        NotFound,
        OtherError
    }

    fun getUser(model: UserModel, completion: (UserResponse?, UserError?) -> Unit)
}

class UserProviderImp: UserProvider {
    override fun getUser(model: UserModel, completion: (UserResponse?, UserProvider.UserError?) -> Unit) {
        val requestModel = UserRequest (
            model.id
        )

        val endpoint = Endpoint(Endpoint.EndpointType.UserData(requestModel))
        val ws = WebService()

        ws.load<UserResponse>(endpoint) { response: UserResponse?, e: WebServiceError? ->
            var error: UserProvider.UserError? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> UserProvider.UserError.NotFound
                    else -> UserProvider.UserError.OtherError
                }
            }

            completion(response, error)
        }
    }
}