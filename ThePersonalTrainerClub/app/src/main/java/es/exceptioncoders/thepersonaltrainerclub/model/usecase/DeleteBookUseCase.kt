package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.provider.BookingProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface DeleteBookUseCase {
    fun deleteBook(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit)
}

class DeleteBookUseCaseImp(private val provider: BookingProvider, private val userProvider: UserProvider): DeleteBookUseCase {
    override fun deleteBook(classId: String, completion: (Boolean, BookingProvider.Error?) -> Unit) {
        provider.deleteBook(classId) { success, error ->
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