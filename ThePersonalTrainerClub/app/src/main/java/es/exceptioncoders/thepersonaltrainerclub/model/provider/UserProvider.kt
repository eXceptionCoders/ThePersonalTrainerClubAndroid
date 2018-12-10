package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserEntity
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserResponse

interface UserProvider {
    enum class UserError {
        NotFound,
        OtherError
    }

    fun getUser(completion: (UserModel?, UserError?) -> Unit)
}

class UserProviderImp: UserProvider {
    override fun getUser(completion: (UserModel?, UserProvider.UserError?) -> Unit) {
        val endpoint = Endpoint(Endpoint.EndpointType.UserData)
        val ws = WebService()
        ws.load<UserResponse>(endpoint) { response: UserResponse?, e: WebServiceError? ->
            var error: UserProvider.UserError? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> UserProvider.UserError.NotFound
                    else -> UserProvider.UserError.OtherError
                }
            }

            completion(mapEntityToModel(response!!.data), error)
        }
    }

    private fun mapEntityToModel(response: UserEntity) : UserModel {
        return UserModel(response._id,
                response.name,
                response.lastname,
                response.coach,
                response.gender,
                response.thumbnail,
                response.email,
                response.locations,
                response.sports,
                response.classes,
                response.activeBookings)
    }
}