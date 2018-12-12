package es.exceptioncoders.thepersonaltrainerclub.model.model

import java.io.Serializable

data class ClassModel(var id: String,
                      var instructor: TrainerModel,
                      var sport: SportModel,
                      var location: LocationModel,
                      var description: String,
                      var price: Double,
                      var maxusers: Int,
                      var duration: Int,
                      var registered: Int?,
                      var place: String,
                      var date: String,
                      var booking: String?): Serializable
