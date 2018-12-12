package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.*
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserEntity
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserResponse
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

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
        var showCoachView = false

        SharedApp.preferences.user?.let { user ->
            showCoachView = user.showCoachView
        } ?: run {
            showCoachView = response.coach
        }

        val locations = mutableListOf<LocationModel>()
        for (location in response.locations) {
            val model = LocationModel(
                    location._id,
                    location.type,
                    location.coordinates,
                    location.description
            )

            locations.add(model)
        }

        val sports = mutableListOf<SportModel>()
        for (sport in response.sports) {
            val model = SportModel(
                    sport._id,
                    sport.name,
                    sport.icon
            )

            sports.add(model)
        }

        val classes = mutableListOf<ClassModel>()
        for (c in response.classes) {
            val trainerModel = TrainerModel(
                    c.instructor._id,
                    c.instructor.name,
                    c.instructor.lastname,
                    c.instructor.thumbnail
            )

            val sportModel = SportModel(
                    c.sport._id,
                    c.sport.name,
                    c.sport.icon
            )

            val locationModel = LocationModel(
                    c.location._id,
                    c.location.type,
                    c.location.coordinates,
                    c.location.description
            )

            val model = ClassModel(
                    c._id,
                    trainerModel,
                    sportModel,
                    locationModel,
                    c.description,
                    c.price,
                    c.maxusers,
                    c.duration,
                    c.registered!!,
                    c.place,
                    ""
            )

            classes.add(model)
        }

        val bookings = mutableListOf<ClassModel>()
        for (book in response.activeBookings) {
            val trainerModel = TrainerModel(
                    book.instructor._id,
                    book.instructor.name,
                    book.instructor.lastname,
                    book.instructor.thumbnail
            )

            val sportModel = SportModel(
                    book.sport._id,
                    book.sport.name,
                    book.sport.icon
            )

            val locationModel = LocationModel(
                    book.location._id,
                    book.location.type,
                    book.location.coordinates,
                    book.location.description
            )

            val model = ClassModel(
                    book._id,
                    trainerModel,
                    sportModel,
                    locationModel,
                    book.description,
                    book.price.toDouble(),
                    book.maxusers,
                    book.duration,
                    book.registered!!,
                    book.place,
                    ""
            )

            bookings.add(model)
        }

        return UserModel(response._id,
                response.name,
                response.lastname,
                response.coach,
                response.gender,
                response.thumbnail ?: "",
                response.email,
                locations.toTypedArray(),
                sports.toTypedArray(),
                classes.toTypedArray(),
                bookings.toTypedArray(),
                showCoachView)
    }
}
