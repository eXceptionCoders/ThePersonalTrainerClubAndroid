package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SetUserThumbnailRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SetUserThumbnailResponse

interface SetThumbnailProvider {
    enum class Error {
        UserPasswordNotFound,
        IncorrectEntry,
        OtherError
    }

    fun setThumbnail(model: SetUserThumbnailRequest, completion: (Boolean, Error?) -> Unit)
}

class SetThumbnailProviderImp: SetThumbnailProvider {
    override fun setThumbnail(model: SetUserThumbnailRequest, completion: (Boolean, SetThumbnailProvider.Error?) -> Unit) {
        val endpoint = Endpoint(Endpoint.EndpointType.SetUserThumbnail(model))

        val ws = WebService()

        ws.load<SetUserThumbnailResponse>(endpoint) { response: SetUserThumbnailResponse?, e: WebServiceError? ->
            var error: SetThumbnailProvider.Error? = null
            var success = false

            e?.let {
                error = when (it) {
                    WebServiceError.Unauthorized -> SetThumbnailProvider.Error.UserPasswordNotFound
                    WebServiceError.UnprocessableEntity -> SetThumbnailProvider.Error.IncorrectEntry
                    else -> SetThumbnailProvider.Error.OtherError
                }
            } ?: kotlin.run {
                success = true
            }

            completion(success, error)
        }
    }
}