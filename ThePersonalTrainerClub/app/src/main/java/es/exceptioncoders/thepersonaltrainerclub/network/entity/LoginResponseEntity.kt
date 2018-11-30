package es.exceptioncoders.thepersonaltrainerclub.network.entity

class LoginResponseData (
    val token: String
)

data class LoginResponse(
    val version: String,
    val status: String,
    val message: String,
    val datetime: String,
    val error: Any,
    val data: LoginResponseData
): BaseResponse(version, status, message, datetime, error)