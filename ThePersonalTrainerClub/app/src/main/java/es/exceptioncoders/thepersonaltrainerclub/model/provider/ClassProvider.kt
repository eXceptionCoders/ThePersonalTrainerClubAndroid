package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.TrainerClassRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.TrainerClassResponse

interface ClassProvider {
    enum class ClassError {
        UnprocessableEntity,
        NotFound,
        OtherError
    }

    fun getClassesForTrainer(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit)
}

class ClassProviderImp: ClassProvider {
    override fun getClassesForTrainer(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit) {
        val requestModel = TrainerClassRequest(
            trainerId
        )

        val endpoint = Endpoint(Endpoint.EndpointType.TrainerClasses(requestModel))
        val ws = WebService()

        ws.load<TrainerClassResponse>(endpoint) { response: TrainerClassResponse?, e: WebServiceError? ->
            var error: ClassProvider.ClassError? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> ClassProvider.ClassError.NotFound
                    WebServiceError.UnprocessableEntity -> ClassProvider.ClassError.UnprocessableEntity
                    else -> ClassProvider.ClassError.OtherError
                }
            }

            completion(response, error)
        }
    }
}