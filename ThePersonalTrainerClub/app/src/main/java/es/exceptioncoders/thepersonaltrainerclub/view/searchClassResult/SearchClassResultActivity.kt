package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.google.gson.Gson
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search_class_result.*

class SearchClassResultActivity : BaseActivity(), SearchClassResultActivityContract.View {
    override fun bindLayout(): Int = R.layout.activity_search_class_result
    private lateinit var classesAdapter: SearchClassResultAdapter
    private lateinit var classes: Array<ClassModel>
    private lateinit var mPresenter: SearchClassResultActivityContract.Presenter<SearchClassResultActivity>

    companion object {
        const val CLASSES_LIST_KEY = "classes"
    }

    override fun localizeView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = SearchClassResultActivityNavigator() as SearchClassResultActivityContract.Navigator<SearchClassResultActivityContract.View>
        mNavigator.attachView(this)

        mPresenter = SearchClassResultActivityPresenter(mNavigator) as SearchClassResultActivityContract.Presenter<SearchClassResultActivity>
        mPresenter.attachView(this)

        classes = Gson().fromJson(intent.extras.getString(CLASSES_LIST_KEY), Array<ClassModel>::class.java)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.search_classes_result_title)

        numberText.text = resources.getString(R.string.search_classes_result_title) + ": ${classes.count()}"
    }


    override fun onResume() {
        super.onResume()

        setUpClassesRecycler()
    }
    private fun setUpClassesRecycler() {
        classesAdapter = SearchClassResultAdapter(classes, object :  OnItemClickListener {
            override fun onItemClick(item: ClassModel) {
                mPresenter.onClassTapped(item)
            }
        }, object: OnBookClickListener {
            override fun onBookClick(item: ClassModel) {
                mPresenter.onBookClassTapped(item)
            }
        })
        searchResultListView.adapter = classesAdapter
        searchResultListView.layoutParams.height = if (classes.count() == 0) 100 else 486 * classes.count()
        searchResultListView.layoutManager = LinearLayoutManager(this)
        searchResultListView.itemAnimator = DefaultItemAnimator()
    }
}
