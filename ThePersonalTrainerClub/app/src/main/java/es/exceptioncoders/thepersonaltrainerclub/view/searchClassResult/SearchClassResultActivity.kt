package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
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

    companion object {
        const val CLASSES_LIST_KEY = "classes"
    }

    override fun localizeView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        classesAdapter = SearchClassResultAdapter(classes)
        searchResultListView.adapter = classesAdapter
        searchResultListView.layoutParams.height = if (classes.count() == 0) 100 else 486 * classes.count()
        searchResultListView.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        searchResultListView.itemAnimator = DefaultItemAnimator()
    }
}
