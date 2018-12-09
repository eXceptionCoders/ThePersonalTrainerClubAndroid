package es.exceptioncoders.thepersonaltrainerclub.network.mapquest.entity

data class MQAddressRequest (
        val location: String,
        val options: MQAddressRequestOptions = MQAddressRequestOptions()
)

data class MQAddressRequestOptions (
        val thumbMaps: Boolean = false,
        var maxResults: Int = 1
)

data class MQAddressResponse (
        val results: Array<MQAddressResponseResults>
)

data class MQAddressResponseResults (
        val providedLocation: MQAddressResponseProvidedStreet,
        val locations: Array<MQAddressResponseLocations>
)

data class MQAddressResponseProvidedStreet (
        val street: String
)

data class MQAddressResponseLocations (
        val street: String,
        val adminArea1: String,
        val adminArea3: String,
        val adminArea5: String,
        val latLng: MQAddressResponseLatLng
)

data class MQAddressResponseLatLng (
        val lat: Double,
        val lng: Double
)