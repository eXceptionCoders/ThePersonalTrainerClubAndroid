package es.exceptioncoders.thepersonaltrainerclub.view.searchClass

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.SeekBar
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityListAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.LocationStripViewAdapter
import kotlinx.android.synthetic.main.fragment_search_class.*

class SearchClassFragment : BaseFragment(), SearchClassFragmentContract.View {
    private lateinit var mPresenter: SearchClassFragmentContract.Presenter<SearchClassFragment>
    private lateinit var activitiesAdapter: ActivityListAdapter
    private lateinit var locationsAdapter: LocationStripViewAdapter

    override fun bindLayout(): Int = R.layout.fragment_search_class

    override fun localizeView() {
        (searchClassButton as Button).text = resources.getString(R.string.search_class_button_search)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = SearchClassFragmentNavigator() as SearchClassFragmentContract.Navigator<SearchClassFragmentContract.View>
        mNavigator.attachView(this)

        mPresenter = SearchClassFragmentPresenter(mNavigator) as SearchClassFragmentContract.Presenter<SearchClassFragment>
        mPresenter.attachView(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setUpActivitiesRecycler()

        radioText.text = resources.getString(R.string.search_class_section03) + ": " + 15 + "Km"
        priceText.text = resources.getString(R.string.search_class_section06) + ": " + resources.getString(R.string.search_class_any)

        radioSeekBar.progress = 15
        priceSeekBar.progress = 0

        radioSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                radioText.text = resources.getString(R.string.search_class_section03) + ": " + i + "Km"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        priceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                priceText.text = resources.getString(R.string.search_class_section06) + ": " + i + "€"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        (searchClassButton as Button).setOnClickListener {
            activitiesAdapter.selected?.let { sport ->
                locationsAdapter.itemSelected?.let { location ->
                    mPresenter.onSearch(sport, location, radioSeekBar.progress*1000, priceSeekBar.progress)
                } ?: kotlin.run {
                    showAlertMessage(null, R.string.new_class_location_error)
                }
            } ?: kotlin.run {
                showAlertMessage(null, R.string.new_class_sport_error)
            }
        }

        mPresenter.fetchUser()

        super.onActivityCreated(savedInstanceState)
    }

    private fun setUpActivitiesRecycler() {
        activitiesAdapter = ActivityListAdapter(true)

        activitiesListView.layoutManager = GridLayoutManager(this.activity, 1, GridLayoutManager.HORIZONTAL, false)
        activitiesListView.itemAnimator = DefaultItemAnimator()
        activitiesListView.adapter = activitiesAdapter
    }

    override fun setUser(user: UserModel) {
        activitiesAdapter.swapData(user.activities.toList())

        locationsAdapter = LocationStripViewAdapter(true, user.locations.toList(), this.activity!!)
        (locationsListview as ListView).adapter = locationsAdapter
        (locationsListview as ListView).layoutParams.height = if (user.locations.count() == 0) 100 else 100 * user.locations.count()
        (locationsListview as ListView).setOnItemClickListener { adapterView, view, i, l ->
            locationsAdapter.itemSelected = user.locations[i]
            locationsAdapter.notifyDataSetChanged()
        }
    }

    override fun resetInputs() {
        radioText.text = resources.getString(R.string.search_class_section03) + ": " + 15 + "Km"
        priceText.text = resources.getString(R.string.search_class_section06) + ": " + resources.getString(R.string.search_class_any)

        radioSeekBar.progress = 15
        priceSeekBar.progress = 0

        activitiesAdapter.selected = null
        activitiesAdapter.notifyDataSetChanged()

        locationsAdapter.itemSelected = null
        locationsAdapter.notifyDataSetChanged()
    }

}