package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.network.entity.TrainerClassResponse
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserResponse

interface TrainerManagementUseCase {
    fun getUser(completion: (UserModel?, UserProvider.UserError?) -> Unit)
    fun getClasses(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit)
}

class TrainerManagementUseCaseImp(private val userProvider: UserProvider, private val classProvider: ClassProvider): TrainerManagementUseCase {
    override fun getUser(completion: (UserModel?, UserProvider.UserError?) -> Unit) {
        userProvider.getUser(completion)
    }

    override fun getClasses(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit) {
        classProvider.getClassesForTrainer(trainerId, completion)
    }
}