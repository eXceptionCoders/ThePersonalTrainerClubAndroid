package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.BookClassRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.BookClassResponse

interface BookingProvider {
    enum class Error {
        UnprocessableEntity,
        NotFound,
        OtherError
    }

    fun bookClass(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit)
    fun deleteBook(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit)
}

class BookingProviderImp: BookingProvider {
    override fun bookClass(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit) {
        val requestModel = BookClassRequest(
                classId
        )

        val endpoint = Endpoint(Endpoint.EndpointType.BookClass(requestModel))
        val ws = WebService()

        ws.load<BookClassResponse>(endpoint) { response: BookClassResponse?, e: WebServiceError? ->
            var error: BookingProvider.Error? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> BookingProvider.Error.NotFound
                    WebServiceError.UnprocessableEntity -> BookingProvider.Error.UnprocessableEntity
                    else -> BookingProvider.Error.OtherError
                }

                completion(false, error)
            } ?: run {
                completion(true, error)
            }
        }
    }

    override fun deleteBook(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit) {
        val requestModel = BookClassRequest(
                classId
        )

        val endpoint = Endpoint(Endpoint.EndpointType.DeleteBook(requestModel))
        val ws = WebService()

        ws.load<BookClassResponse>(endpoint) { response: BookClassResponse?, e: WebServiceError? ->
            var error: BookingProvider.Error? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> BookingProvider.Error.NotFound
                    WebServiceError.UnprocessableEntity -> BookingProvider.Error.UnprocessableEntity
                    else -> BookingProvider.Error.OtherError
                }

                completion(false, error)
            } ?: run {
                completion(true, error)
            }
        }
    }
}