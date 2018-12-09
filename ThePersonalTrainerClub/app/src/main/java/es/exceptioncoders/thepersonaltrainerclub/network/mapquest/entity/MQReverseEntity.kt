package es.exceptioncoders.thepersonaltrainerclub.network.mapquest.entity

data class MQReverseRequest (
        val location: MQReverseRequestLocation,
        val options: MQReverseRequestOptions = MQReverseRequestOptions(),
        val includeNearestIntersection: Boolean = false,
        val includeRoadMetadata: Boolean = false
)

data class MQReverseRequestLocation (
        val latLng: MQReverseRequestLatLng
)

data class MQReverseRequestLatLng (
        val lat: Double,
        val lng: Double
)

data class MQReverseRequestOptions (
        val thumbMaps: Boolean = false,
        var maxResults: Int = 1
)

data class MQReverseResponse (
        val results: Array<MQAddressResponseResults>
)

data class MQReverseResponseResults (
        val providedLocation: MQAddressResponseProvidedStreet,
        val locations: Array<MQAddressResponseLocations>
)

data class MQReverseResponseProvidedStreet (
        val street: String
)

data class MQReverseResponseLocations (
        val street: String,
        val adminArea1: String,
        val adminArea3: String,
        val adminArea5: String,
        val latLng: MQAddressResponseLatLng
)

data class MQReverseResponseLatLng (
        val lat: Double,
        val lng: Double
)