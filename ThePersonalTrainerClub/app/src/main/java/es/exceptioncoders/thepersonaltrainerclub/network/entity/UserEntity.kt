package es.exceptioncoders.thepersonaltrainerclub.network.entity

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import java.time.LocalDateTime

class UserRequest (
    val id: String
)

class UserEntity (
        val id: String,
        val name: String,
        val lastname: String,
        val coach: Boolean,
        val birthday: LocalDateTime,
        val gender: String,
        val thumbnail: String,
        val email: String,
        val locations: Array<LocationModel>,
        val activities: Array<SportModel>,
        val description: String
)

data class UserResponse (
    val version: String,
    val status: String,
    val message: String,
    val datetime: String,
    val error: Any,
    val data: UserEntity
) : BaseResponse(version, status, message, datetime, error)