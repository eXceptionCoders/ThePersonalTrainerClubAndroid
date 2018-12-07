package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ActivityProvider

interface ActivityUseCase {
    fun getAllActivities(completion: (Array<SportModel>, ActivityProvider.ActivityError?) -> Unit)
}

class ActivityUseCaseImp(private val provider: ActivityProvider): ActivityUseCase {
    override fun getAllActivities(completion: (Array<SportModel>, ActivityProvider.ActivityError?) -> Unit) {
        provider.fetchActivities(completion)
    }
}