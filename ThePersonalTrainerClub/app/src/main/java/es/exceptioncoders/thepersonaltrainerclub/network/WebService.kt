package es.exceptioncoders.thepersonaltrainerclub.network

import com.google.gson.Gson
import java.lang.Exception

enum class WebServiceError {
    RequestError,
    DecodingError,
    ForbiddenError,
    OtherError
}

class WebService {
    inline fun <reified O>load(endpoint: Endpoint, crossinline completion: (O?, WebServiceError?) -> Unit) {
        endpoint.request()
        .response { request, response, result ->
            var e: WebServiceError? = null
            var v: O? = null

            when (response.statusCode) {
                -1 -> e = WebServiceError.RequestError
                200, 201 -> {
                    try {
                        v = Gson().fromJson(response.responseMessage, O::class.java)
                    } catch (exception: Exception) {
                        e = WebServiceError.DecodingError
                    }
                }
                401 -> e = WebServiceError.ForbiddenError
                else -> e = WebServiceError.OtherError
            }

            completion(v, e)
        }
    }
}