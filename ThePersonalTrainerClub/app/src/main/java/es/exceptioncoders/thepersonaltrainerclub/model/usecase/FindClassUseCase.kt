package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.FindClassesModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProvider

interface FindClassUseCase {
    fun find(sport: String, location: LocationModel, distance: Int, price: String, page: Int, perPage: Int, completion: (FindClassesModel?, ClassProvider.ClassError?) -> Unit)
}

class FindClassUseCaseImp(private val provider: ClassProvider): FindClassUseCase {
    override fun find(sport: String, location: LocationModel, distance: Int, price: String, page: Int, perPage: Int, completion: (FindClassesModel?, ClassProvider.ClassError?) -> Unit) {
        provider.find(sport, location, distance, price, page, perPage, completion)
    }
}