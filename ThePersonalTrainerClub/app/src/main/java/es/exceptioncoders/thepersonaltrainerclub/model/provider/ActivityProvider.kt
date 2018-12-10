package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SportEntity
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SportResponse

interface ActivityProvider {
    enum class ActivityError {
        UserPasswordNotFound,
        IncorrectEntry,
        OtherError
    }

    fun fetchActivities(completion: (Array<SportModel>, ActivityError?) -> Unit)
}

class ActivityProviderImp: ActivityProvider {
    override fun fetchActivities(completion: (Array<SportModel>, ActivityProvider.ActivityError?) -> Unit) {
        val endpoint = Endpoint(Endpoint.EndpointType.Activities)

        val ws = WebService()

        ws.load<SportResponse>(endpoint) { response: SportResponse?, e: WebServiceError? ->
            var error: ActivityProvider.ActivityError? = null

            e?.let {
                error = when (it) {
                    WebServiceError.Unauthorized -> ActivityProvider.ActivityError.UserPasswordNotFound
                    WebServiceError.UnprocessableEntity -> ActivityProvider.ActivityError.IncorrectEntry
                    else -> ActivityProvider.ActivityError.OtherError
                }
            } ?: kotlin.run {

            }

            completion(mapEntityToModel(response!!.data), error)
        }
    }

    private fun mapEntityToModel(response: Array<SportEntity>) : Array<SportModel> {
        var array = mutableListOf<SportModel>()

        for (sport in response) {
            var model = SportModel(sport._id,
                    sport.name,
                    sport.icon)

            array.add(model)
        }

        return array.toTypedArray()
    }
}