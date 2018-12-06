package es.exceptioncoders.thepersonaltrainerclub.network

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.network.entity.*
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

class Endpoint(private val type: EndpointType) {
    init {
        FuelManager.instance.basePath = "https://thepersonaltrainerclubapi-dev.azurewebsites.net"
    }

    sealed class EndpointType {
        class Login(val loginRequestModel: LoginRequest) : EndpointType()
        class Register(val registerRequestModel: RegisterRequest) : EndpointType()
        class UserData(val userRequestModel: UserRequest) : EndpointType()
        class TrainerClasses(val trainerClassRequestModel: TrainerClassRequest) : EndpointType()
    }

    fun request(): Request {
        return method().also {
            it.headers.clear()
        }.header(Pair("Content-Type", "application/json"), Pair("x-access-token", SharedApp.preferences.jwtToken))
                .body(parameters())
    }

    private fun path(): String {
        return when (type) {
            is EndpointType.Login -> "/api/v1/es/users/login"
            is EndpointType.Register -> "/api/v1/es/users/signup"
            is EndpointType.UserData -> "/api/v1/es/data/user"
            is EndpointType.TrainerClasses -> "/api/v1/es/classes/trainers"
        }
    }

    private fun parameters(): String {
        return when (type) {
            is EndpointType.Login -> Gson().toJson(type.loginRequestModel)
            is EndpointType.Register -> Gson().toJson(type.registerRequestModel)
            is EndpointType.UserData -> Gson().toJson(type.userRequestModel)
            is EndpointType.TrainerClasses -> Gson().toJson(type.trainerClassRequestModel)
        }
    }

    private fun method(): Request {
        return when (type) {
            is EndpointType.Login -> path().httpPost()
            is EndpointType.Register -> path().httpPost()
            is EndpointType.UserData -> path().httpGet()
            is EndpointType.TrainerClasses -> path().httpGet()
        }
    }

}