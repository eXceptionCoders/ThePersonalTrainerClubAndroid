package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.BookingProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.DeleteBookUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.DeleteBookUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class TrainerManagementFragmentPresenter(private val mNavigator: TrainerManagementFragmentContract.Navigator<TrainerManagementFragmentContract.View>) : BasePresenter<TrainerManagementFragmentContract.View>(), TrainerManagementFragmentContract.Presenter<TrainerManagementFragmentContract.View> {
    private val useCase: DeleteBookUseCase = DeleteBookUseCaseImp(BookingProviderImp(), UserProviderImp())

    override fun onClassClicked(position: Int) {
        SharedApp.preferences.user?.let {
            this.mNavigator.navigateToClassDetail( if(it.showCoachView) it.classes[position] else it.activeBookings[position])
        }
    }

    override fun onDeleteBookClicked(model: ClassModel) {
        if (SharedApp.preferences.user!!.showCoachView) {
            mView?.showAlertMessage(null, R.string.trainer_management_delete_class_error)
        } else {
            mView?.showLoading()

            model.booking?.let {
                useCase.deleteBook(it) { success, error ->
                    mView?.hideLoading()

                    error?.let {
                        mView?.showAlertMessage(null, R.string.trainer_management_delete_book_error)
                    } ?: kotlin.run {
                        mView?.refreshView()
                    }
                }
            } ?: run {
                mView?.showAlertMessage(null, R.string.trainer_management_delete_book_error)
            }
        }






    }
}