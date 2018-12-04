package es.exceptioncoders.thepersonaltrainerclub.network.entity

data class LoginRequest (
    val email: String,
    val password: String
)

class LoginEntity (
    val token: String
)

data class LoginResponse(
    val version: String,
    val status: String,
    val message: String,
    val datetime: String,
    val error: Any,
    val data: LoginEntity
): BaseResponse(version, status, message, datetime, error)