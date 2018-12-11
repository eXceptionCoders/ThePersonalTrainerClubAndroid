package es.exceptioncoders.thepersonaltrainerclub.view.searchClass

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator
import android.content.Intent
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult.SearchClassResultActivity


class SearchClassFragmentNavigator : BaseNavigator<SearchClassFragmentContract.View>(), SearchClassFragmentContract.Navigator<SearchClassFragmentContract.View> {
    override fun navigateToSerachClassResult(classList: ArrayList<ClassModel>) {
        val intent = Intent((mView as SearchClassFragment).activity, SearchClassResultActivity::class.java)

        val json = Gson().toJson(classList)
        intent.putExtra(SearchClassResultActivity.CLASSES_LIST_KEY, json)
        (mView as SearchClassFragment).activity?.startActivity(intent)
    }
}