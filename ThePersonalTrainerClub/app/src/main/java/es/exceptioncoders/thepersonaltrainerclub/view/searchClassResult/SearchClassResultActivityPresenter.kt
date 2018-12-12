package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class SearchClassResultActivityPresenter(private val mNavigator: SearchClassResultActivityContract.Navigator<SearchClassResultActivityContract.View>) : BasePresenter<SearchClassResultActivityContract.View>(), SearchClassResultActivityContract.Presenter<SearchClassResultActivityContract.View> {
    override fun onClassTapped(item: ClassModel) {
        mNavigator.navigateToClassDetail(item)
    }

    override fun onBookClassTapped(item: ClassModel) {

    }
}
