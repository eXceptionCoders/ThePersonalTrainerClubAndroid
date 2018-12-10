package es.exceptioncoders.thepersonaltrainerclub.network.entity

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel

class UserEntity (
        val _id: String,
        val name: String,
        val lastname: String,
        val coach: Boolean,
        val gender: String,
        val thumbnail: String,
        val email: String,
        val locations: Array<LocationModel>,
        val sports: Array<SportModel>,
        val classes: Array<ClassModel>,
        val activeBookings: Array<ClassModel>
)

data class UserResponse (
    val version: String,
    val status: String,
    val message: String,
    val datetime: String,
    val error: Any,
    val data: UserEntity
) : BaseResponse(version, status, message, datetime, error)