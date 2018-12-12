package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import android.content.Intent
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import es.exceptioncoders.thepersonaltrainerclub.view.classDetail.ClassDetailActivity

class SearchClassResultActivityNavigator : BaseNavigator<SearchClassResultActivityContract.View>(), SearchClassResultActivityContract.Navigator<SearchClassResultActivityContract.View> {
    override fun navigateToClassDetail(model: ClassModel) {
        val intent = Intent((mView as SearchClassResultActivity), ClassDetailActivity::class.java)
        intent.putExtra(ClassDetailActivity.MODEL_CLASS_KEY, model)
        (mView as SearchClassResultActivity).startActivity(intent)
    }

    override fun popBack() {
        (mView as SearchClassResultActivity).finish()
    }
}
