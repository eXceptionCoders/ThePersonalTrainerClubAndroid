package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.MQAddressModel
import es.exceptioncoders.thepersonaltrainerclub.network.mapquest.MQEndpoint
import es.exceptioncoders.thepersonaltrainerclub.network.mapquest.MQWebService
import es.exceptioncoders.thepersonaltrainerclub.network.mapquest.MQWebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.mapquest.entity.*
import org.osmdroid.util.GeoPoint

interface MapQuestProvider {
    enum class Error {
        UnprocessableEntity,
        NotFound,
        OtherError
    }

    fun getLocation(model: MQAddressModel, completion: (MQAddressModel?, MapQuestProvider.Error?) -> Unit)
    fun getReverseLocation(model: MQAddressModel, completion: (MQAddressModel?, MapQuestProvider.Error?) -> Unit)
}

class MapQuestProviderProviderImp: MapQuestProvider {

    override fun getLocation(model: MQAddressModel, completion: (MQAddressModel?, MapQuestProvider.Error?) -> Unit) {
        val endpoint = MQEndpoint(MQEndpoint.EndpointType.Address(mapModelToAddressRequest(model)))
        val ws = MQWebService()

        ws.load<MQAddressResponse>(endpoint) { response: MQAddressResponse?, e: MQWebServiceError? ->
            var error: MapQuestProvider.Error? = null

            e?.let {
                error = when (it) {
                    MQWebServiceError.NotFound -> MapQuestProvider.Error.NotFound
                    MQWebServiceError.UnprocessableEntity -> MapQuestProvider.Error.UnprocessableEntity
                    else -> MapQuestProvider.Error.OtherError
                }

                completion(null, error)
            } ?: run {
                completion(mapAddressResponseToModel(response!!), error)
            }
        }
    }

    override fun getReverseLocation(model: MQAddressModel, completion: (MQAddressModel?, MapQuestProvider.Error?) -> Unit) {
        val endpoint = MQEndpoint(MQEndpoint.EndpointType.Reverse(mapModelToReverseRequest(model)))
        val ws = MQWebService()

        ws.load<MQReverseResponse>(endpoint) { response: MQReverseResponse?, e: MQWebServiceError? ->
            var error: MapQuestProvider.Error? = null

            e?.let {
                error = when (it) {
                    MQWebServiceError.NotFound -> MapQuestProvider.Error.NotFound
                    MQWebServiceError.UnprocessableEntity -> MapQuestProvider.Error.UnprocessableEntity
                    else -> MapQuestProvider.Error.OtherError
                }

                completion(null, error)
            } ?: run {
                completion(mapReverseResponseToModel(response!!), error)
            }
        }
    }

    private fun mapModelToAddressRequest(model: MQAddressModel): MQAddressRequest {
        return MQAddressRequest(model.address)
    }

    private fun mapAddressResponseToModel(response: MQAddressResponse): MQAddressModel? {
        if (response.results.count() > 0) {
            if (response.results[0].locations.count() > 0) {
                var street: String

                if (response.results[0].locations[0].street.isEmpty() || response.results[0].locations[0].street.isBlank()) {
                    street = response.results[0].providedLocation.street
                } else {
                    street = response.results[0].locations[0].street
                }

                return MQAddressModel(street + ", " +
                        response.results[0].locations[0].adminArea5 + ", " +
                        response.results[0].locations[0].adminArea3 + ", " +
                        response.results[0].locations[0].adminArea1,
                        GeoPoint(response.results[0].locations[0].latLng.lat, response.results[0].locations[0].latLng.lng))
            }
        }

        return null
    }

    private fun mapModelToReverseRequest(model: MQAddressModel): MQReverseRequest {
        return MQReverseRequest(MQReverseRequestLocation(MQReverseRequestLatLng(model.latlng.latitude, model.latlng.longitude)))
    }

    private fun mapReverseResponseToModel(response: MQReverseResponse): MQAddressModel? {
        if (response.results.count() > 0) {
            if (response.results[0].locations.count() > 0) {
                var street: String

                if (response.results[0].locations[0].street.isEmpty() || response.results[0].locations[0].street.isBlank()) {
                    street = response.results[0].providedLocation.street
                } else {
                    street = response.results[0].locations[0].street
                }

                return MQAddressModel(street + ", " +
                        response.results[0].locations[0].adminArea5 + ", " +
                        response.results[0].locations[0].adminArea3 + ", " +
                        response.results[0].locations[0].adminArea1,
                        GeoPoint(response.results[0].locations[0].latLng.lat, response.results[0].locations[0].latLng.lng))
            }
        }

        return null
    }
}