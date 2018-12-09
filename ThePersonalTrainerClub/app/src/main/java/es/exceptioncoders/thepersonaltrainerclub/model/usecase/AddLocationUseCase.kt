package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LocationProvider

interface AddLocationUseCase {
    fun addLocation(model: LocationModel, completion: (Boolean, LocationProvider.Error?) -> Unit)
}

class AddLocationUseCaseImp(private val provider: LocationProvider): AddLocationUseCase {
    override fun addLocation(model: LocationModel, completion: (Boolean, LocationProvider.Error?) -> Unit) {
        provider.addLocation(model, completion)
    }
}