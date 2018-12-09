package es.exceptioncoders.thepersonaltrainerclub.network.mapquest

import com.google.gson.Gson
import java.lang.Exception

enum class MQWebServiceError {
    RequestError,
    DecodingError,
    Unauthorized,
    UnprocessableEntity,
    OtherError,
    BadRequest,
    ForbiddenError,
    NotFound,
    InternalServerError,
    DuplicateError
}

class MQWebService {
    inline fun <reified O>load(endpoint: MQEndpoint, crossinline completion: (O?, MQWebServiceError?) -> Unit) {
        endpoint.request().response { request, response, result ->
            var e: MQWebServiceError? = null
            var v: O? = null

            when (response.statusCode) {
                -1 -> e = MQWebServiceError.RequestError
                200, 201 -> {
                    try {
                        v = Gson().fromJson(String(response.data), O::class.java)
                    } catch (exception: Exception) {
                        e = MQWebServiceError.DecodingError
                    }
                }
                400 -> e = MQWebServiceError.BadRequest
                401 -> e = MQWebServiceError.Unauthorized
                403 -> e = MQWebServiceError.ForbiddenError
                404 -> e = MQWebServiceError.NotFound
                422 -> {
                    try {
                        v = Gson().fromJson(String(response.data), O::class.java)
                        e = MQWebServiceError.UnprocessableEntity
                    } catch (exception: Exception) {
                        e = MQWebServiceError.DecodingError
                    }
                }
                500 -> e = MQWebServiceError.InternalServerError
                else -> e = MQWebServiceError.OtherError
            }

            completion(v, e)
        }
    }
}