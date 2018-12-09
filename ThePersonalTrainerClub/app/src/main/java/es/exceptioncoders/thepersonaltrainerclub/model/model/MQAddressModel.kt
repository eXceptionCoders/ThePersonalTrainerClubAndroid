package es.exceptioncoders.thepersonaltrainerclub.model.model

import org.osmdroid.util.GeoPoint

data class MQAddressModel (
        val address: String,
        val latlng: GeoPoint
)