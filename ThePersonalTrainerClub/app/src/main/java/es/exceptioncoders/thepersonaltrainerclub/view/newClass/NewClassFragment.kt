package es.exceptioncoders.thepersonaltrainerclub.view.newClass

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.SeekBar
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityListAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.LocationStripViewAdapter
import kotlinx.android.synthetic.main.fragment_new_class.*

class NewClassFragment : BaseFragment(), NewClassFragmentContract.View {
    private lateinit var mPresenter: NewClassFragmentContract.Presenter<NewClassFragment>
    private lateinit var activitiesAdapter: ActivityListAdapter
    private lateinit var locationsAdapter: LocationStripViewAdapter

    override fun bindLayout(): Int = R.layout.fragment_new_class

    override fun localizeView() {
        (createClassButton as Button).text = resources.getString(R.string.add_class_save_class_button_title)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = NewClassFragmentNavigator() as NewClassFragmentContract.Navigator<NewClassFragmentContract.View>
        mNavigator.attachView(this)

        mPresenter = NewClassFragmentPresenter(mNavigator) as NewClassFragmentContract.Presenter<NewClassFragment>
        mPresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setUpActivitiesRecycler()

        priceText.text = resources.getString(R.string.add_class_section_8) + ": " + 15 + "€"
        maxText.text = resources.getString(R.string.add_class_section_7) + ": " + 20

        maxSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                maxText.text = resources.getString(R.string.add_class_section_7) + ": " + i
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        priceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                priceText.text = resources.getString(R.string.add_class_section_8) + ": " + i + "€"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        (createClassButton as Button).setOnClickListener {
            activitiesAdapter.selected?.let { sport ->
                locationsAdapter.itemSelected?.let { location ->
                    mPresenter.onCreate(sport, location, editText.text.toString(), priceSeekBar.progress.toDouble(), maxSeekBar.progress)
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

    override fun resetInputs() {
        priceText.text = resources.getString(R.string.add_class_section_8) + ": " + 15 + "€"
        maxText.text = resources.getString(R.string.add_class_section_7) + ": " + 20

        priceSeekBar.progress = 15
        maxSeekBar.progress = 20

        editText.setText("")

        activitiesAdapter.selected = null
        activitiesAdapter.notifyDataSetChanged()

        locationsAdapter.itemSelected = null
        locationsAdapter.notifyDataSetChanged()
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
}
