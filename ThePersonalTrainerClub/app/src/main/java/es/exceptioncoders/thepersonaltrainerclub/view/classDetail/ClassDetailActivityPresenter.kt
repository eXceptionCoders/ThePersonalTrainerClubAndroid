package es.exceptioncoders.thepersonaltrainerclub.view.classDetail

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.BookingProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.BookClassUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.BookClassUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class ClassDetailActivityPresenter(private val mNavigator: ClassDetailActivityContract.Navigator<ClassDetailActivityContract.View>) : BasePresenter<ClassDetailActivityContract.View>(), ClassDetailActivityContract.Presenter<ClassDetailActivityContract.View> {
    private val useCase: BookClassUseCase = BookClassUseCaseImp(BookingProviderImp(), UserProviderImp())

    override fun onBookClass(model: ClassModel) {
        mView?.showLoading()

        useCase.bookClass(model.id) { success, error ->
            mView?.hideLoading()

            error?.let {
                mView?.showAlertMessage(null, R.string.search_classes_book_error)
            } ?: kotlin.run {
                mNavigator.popBack()
            }
        }
    }
}
