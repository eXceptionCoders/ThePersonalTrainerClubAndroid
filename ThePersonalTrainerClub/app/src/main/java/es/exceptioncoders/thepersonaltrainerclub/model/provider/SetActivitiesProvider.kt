package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.SetSportsModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SetSportRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SetSportResponse

interface SetActivitiesProvider {
    enum class SetActivitiesError {
        UserPasswordNotFound,
        IncorrectEntry,
        OtherError
    }

    fun setSports(model: SetSportsModel, completion: (Boolean, SetActivitiesError?) -> Unit)
}

class SetActivitiesProviderImp: SetActivitiesProvider {
    override fun setSports(model: SetSportsModel, completion: (Boolean, SetActivitiesProvider.SetActivitiesError?) -> Unit) {
        val requestModel = SetSportRequest(model.activities)

        val endpoint = Endpoint(Endpoint.EndpointType.SetActivities(requestModel))

        val ws = WebService()

        ws.load<SetSportResponse>(endpoint) { response: SetSportResponse?, e: WebServiceError? ->
            var error: SetActivitiesProvider.SetActivitiesError? = null
            var success = false

            e?.let {
                error = when (it) {
                    WebServiceError.Unauthorized -> SetActivitiesProvider.SetActivitiesError.UserPasswordNotFound
                    WebServiceError.UnprocessableEntity -> SetActivitiesProvider.SetActivitiesError.IncorrectEntry
                    else -> SetActivitiesProvider.SetActivitiesError.OtherError
                }
            } ?: kotlin.run {
                success = true
            }

            completion(success, error)
        }
    }
}