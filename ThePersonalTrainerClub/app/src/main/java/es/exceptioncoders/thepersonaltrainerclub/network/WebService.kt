package es.exceptioncoders.thepersonaltrainerclub.network

import com.google.gson.Gson
import java.lang.Exception

enum class WebServiceError {
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

class WebService {
    inline fun <reified O>load(endpoint: Endpoint, crossinline completion: (O?, WebServiceError?) -> Unit) {
        endpoint.request().response { request, response, result ->
            var e: WebServiceError? = null
            var v: O? = null

            when (response.statusCode) {
                -1 -> e = WebServiceError.RequestError
                200, 201 -> {
                    try {
                        v = Gson().fromJson(String(response.data), O::class.java)
                    } catch (exception: Exception) {
                        e = WebServiceError.DecodingError
                    }
                }
                400 -> e = WebServiceError.BadRequest
                401 -> e = WebServiceError.Unauthorized
                403 -> e = WebServiceError.ForbiddenError
                404 -> e = WebServiceError.NotFound
                422 -> {
                    try {
                        v = Gson().fromJson(String(response.data), O::class.java)
                        e = WebServiceError.UnprocessableEntity
                    } catch (exception: Exception) {
                        e = WebServiceError.DecodingError
                    }
                }
                500 -> e = WebServiceError.InternalServerError
                else -> e = WebServiceError.OtherError
            }

            completion(v, e)
        }
    }
}