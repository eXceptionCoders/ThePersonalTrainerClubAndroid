package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.AddLocationRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.AddLocationResponse

interface LocationProvider {
    enum class Error {
        UnprocessableEntity,
        NotFound,
        OtherError
    }

    fun addLocation(model: LocationModel, completion: (Boolean, LocationProvider.Error?) -> Unit)
}

class LocationProviderImp: LocationProvider {
    override fun addLocation(model: LocationModel, completion: (Boolean, LocationProvider.Error?) -> Unit) {
        val requestModel = AddLocationRequest(
                model.description,
                model.coordinates[0].toDouble(),
                model.coordinates[1].toDouble()
        )

        val endpoint = Endpoint(Endpoint.EndpointType.AddLocation(requestModel))
        val ws = WebService()

        ws.load<AddLocationResponse>(endpoint) { response: AddLocationResponse?, e: WebServiceError? ->
            var error: LocationProvider.Error? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> LocationProvider.Error.NotFound
                    WebServiceError.UnprocessableEntity -> LocationProvider.Error.UnprocessableEntity
                    else -> LocationProvider.Error.OtherError
                }

                completion(false, error)
            }

            completion(true, error)
        }
    }
}