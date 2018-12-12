package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.BookingProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.BookClassUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.BookClassUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class SearchClassResultActivityPresenter(private val mNavigator: SearchClassResultActivityContract.Navigator<SearchClassResultActivityContract.View>) : BasePresenter<SearchClassResultActivityContract.View>(), SearchClassResultActivityContract.Presenter<SearchClassResultActivityContract.View> {
    private val useCase: BookClassUseCase = BookClassUseCaseImp(BookingProviderImp(), UserProviderImp())

    override fun onClassTapped(item: ClassModel) {
        mNavigator.navigateToClassDetail(item)
    }

    override fun onBookClassTapped(item: ClassModel) {
        mView?.showLoading()

        useCase.bookClass(item.id) { success, error ->
            mView?.hideLoading()

            error?.let {
                mView?.showAlertMessage(null, R.string.search_classes_book_error)
            } ?: kotlin.run {
                mNavigator.popBack()
            }
        }
    }
}
