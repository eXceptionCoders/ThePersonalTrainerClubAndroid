package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.NewClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.*
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.*

interface ClassProvider {
    enum class ClassError {
        UnprocessableEntity,
        NotFound,
        OtherError
    }

    fun getClassesForTrainer(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit)
    fun create(model: NewClassModel, completion: (Boolean, ClassProvider.ClassError?) -> Unit)
    fun find(sport: String, location: LocationModel, distance: Int, price: String, page: Int, perPage: Int, completion: (FindClassesModel?, ClassProvider.ClassError?) -> Unit)
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

    override fun create(model: NewClassModel, completion: (Boolean, ClassProvider.ClassError?) -> Unit) {
        val requestModel = NewClassRequest(
                model.instructor,
                model.sport,
                LocationEntity(model.location.id,
                        model.location.type,
                        model.location.description,
                        model.location.coordinates),
                model.description,
                model.price,
                model.duration,
                model.maxusers
        )

        val endpoint = Endpoint(Endpoint.EndpointType.NewClass(requestModel))
        val ws = WebService()

        ws.load<NewClassResponse>(endpoint) { response: NewClassResponse?, e: WebServiceError? ->
            var error: ClassProvider.ClassError? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> ClassProvider.ClassError.NotFound
                    WebServiceError.UnprocessableEntity -> ClassProvider.ClassError.UnprocessableEntity
                    else -> ClassProvider.ClassError.OtherError
                }

                completion(false, error)
            }

            completion(true, error)
        }
    }

    override fun find(sport: String, location: LocationModel, distance: Int, price: String, page: Int, perPage: Int, completion: (FindClassesModel?, ClassProvider.ClassError?) -> Unit) {
        val requestModel = FindClassRequest(
                sport,
                location.coordinates[0].toDouble(),
                location.coordinates[1].toDouble(),
                distance,
                price,
                page,
                perPage
        )

        val endpoint = Endpoint(Endpoint.EndpointType.SearchClass(requestModel))
        val ws = WebService()

        ws.load<FindClassResponse>(endpoint) { response: FindClassResponse?, e: WebServiceError? ->
            var error: ClassProvider.ClassError? = null

            e?.let {
                error = when (it) {
                    WebServiceError.NotFound -> ClassProvider.ClassError.NotFound
                    WebServiceError.UnprocessableEntity -> ClassProvider.ClassError.UnprocessableEntity
                    else -> ClassProvider.ClassError.OtherError
                }

                completion(null, error)
            }

            completion(mapFindEntityToModel(response!!), error)
        }
    }

    private fun mapFindEntityToModel(data: FindClassResponse): FindClassesModel {
        var total = 0
        data.total?.let {
            total = it
        }

        var classes = listOf<ClassModel>()
        data.data?.let {
            classes = it.map {
                mapEntityToModel(it)
            }
        }

        return FindClassesModel(
                total,
                classes.toTypedArray()
        )
    }

    private fun mapEntityToModel(data: ClassEntity): ClassModel {
        return ClassModel(
                data._id,
                TrainerModel(
                        data.instructor._id,
                        data.instructor.name,
                        data.instructor.lastname,
                        data.instructor.thumbnail ?: ""
                ),
                SportModel(
                        data.sport._id,
                        data.sport.name,
                        data.sport.icon
                ),
                LocationModel(
                        "",
                        data.location.type,
                        data.location.coordinates,
                        data.place
                ),
                data.description,
                data.price,
                data.maxusers,
                data.duration,
                data.registered,
                data.place,
                ""
        )
    }
}