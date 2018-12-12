package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.provider.BookingProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface BookClassUseCase {
    fun bookClass(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit)
}

class BookClassUseCaseImp(private val provider: BookingProvider, private val userProvider: UserProvider): BookClassUseCase {
    override fun bookClass(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit) {
        provider.bookClass(classId) { success, error ->
            if (success) {
                userProvider.getUser { user, error ->
                    user?.let {
                        SharedApp.preferences.user = user

                        completion(true, null)
                    }
                }
            } else {
                completion(false, error)
            }
        }
    }
}