package es.exceptioncoders.thepersonaltrainerclub.network

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.network.entity.*

class Endpoint(private val type: EndpointType) {
    init {
        FuelManager.instance.basePath = "https://thepersonaltrainerappapi.azurewebsites.net"
    }

    sealed class EndpointType {
        class Login(val loginRequestModel: LoginRequest) : EndpointType()
        class Register(val registerRequestModel: RegisterRequest) : EndpointType()
    }

    fun request(): Request {
        return method().also {
            it.headers.clear()
        }.header(Pair("Content-Type", "application/json"))
                .body(parameters())
    }

    private fun path(): String {
        return when (type) {
            is EndpointType.Login -> "/api/v1/es/users/login"
            is EndpointType.Register -> "/api/v1/es/users/signup"
        }
    }

    private fun parameters(): String {
        return when (type) {
            is EndpointType.Login -> Gson().toJson(type.loginRequestModel)
            is EndpointType.Register -> Gson().toJson(type.registerRequestModel)
        }
    }

    private fun method(): Request {
        return when (type) {
            is EndpointType.Login -> path().httpPost()
            is EndpointType.Register -> path().httpPost()
        }
    }

}