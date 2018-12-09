package es.exceptioncoders.thepersonaltrainerclub.network.mapquest

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.network.mapquest.entity.MQAddressRequest
import es.exceptioncoders.thepersonaltrainerclub.network.mapquest.entity.MQReverseRequest

class MQEndpoint(private val type: EndpointType) {
    companion object {
        private val API_KEY = "ESCRIBE_AQUI_EL_API_KEY"
    }

    init {
        FuelManager.instance.basePath = "https://www.mapquestapi.com/geocoding/v1"
    }

    sealed class EndpointType {
        class Address(val model: MQAddressRequest) : EndpointType()
        class Reverse(val model: MQReverseRequest) : EndpointType()
    }

    fun request(): Request {
        return method().also {
            it.headers.clear()
        }.header(Pair("Content-Type", "application/json"))
                .body(parameters())
    }

    private fun path(): String {
        return when (type) {
            is EndpointType.Address -> "/address?key=$API_KEY&outFormat=json"
            is EndpointType.Reverse -> "/reverse?key=$API_KEY&outFormat=json"
        }
    }

    private fun parameters(): String {
        return when (type) {
            is EndpointType.Address -> Gson().toJson(type.model)
            is EndpointType.Reverse -> Gson().toJson(type.model)
        }
    }

    private fun method(): Request {
        return when (type) {
            is EndpointType.Address -> path().httpPost()
            is EndpointType.Reverse -> path().httpPost()
        }
    }
}