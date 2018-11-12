package es.exceptioncoders.thepersonaltrainerclub.network

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.network.entity.LoginRequest

class Endpoint(private val type: EndpointType) {
    init {
        FuelManager.instance.basePath = "https://thepersonaltrainerclubapi.azurewebsites.net"
    }

    sealed class EndpointType {
        class Login(val requestModel: LoginRequest) : EndpointType()
    }

    fun request(): Request {
        return method()
                .header(Pair("Content-Type", "application/json"))
                .body(parameters())
    }

    private fun path(): String {
        return when (type) {
            is EndpointType.Login -> "/api/v1/es/users/login"
        }
    }

    private fun parameters(): String {
        return when (type) {
            is EndpointType.Login -> Gson().toJson(type.requestModel)
        }
    }

    private fun method(): Request {
        return when (type) {
            is EndpointType.Login -> path().httpPost()
        }
    }

}