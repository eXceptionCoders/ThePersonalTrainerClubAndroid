package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityListAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ClassStripViewAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.LocationStripViewAdapter
import kotlinx.android.synthetic.main.activity_class_list.view.*
import kotlinx.android.synthetic.main.activity_location_list.view.*
import kotlinx.android.synthetic.main.fragment_trainer_management.*

class TrainerManagementFragment : BaseFragment(), TrainerManagementFragmentContract.View {
    private lateinit var mPresenter: TrainerManagementFragmentContract.Presenter<TrainerManagementFragment>
    private lateinit var locationsAdapter: LocationStripViewAdapter
    private lateinit var classesAdapter: ClassStripViewAdapter
    private lateinit var activitiesAdapter: ActivityListAdapter

    override fun bindLayout(): Int = R.layout.fragment_trainer_management

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = TrainerManagementFragmentNavigator() as TrainerManagementFragmentContract.Navigator<TrainerManagementFragmentContract.View>
        mNavigator.attachView(this)

        mPresenter = TrainerManagementFragmentPresenter(mNavigator) as TrainerManagementFragmentContract.Presenter<TrainerManagementFragment>
        mPresenter.attachView(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpActivitiesRecycler()
        showUserData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_trainer_management, container, false)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun localizeView() {
        sportsLabel.text = getString(R.string.trainer_management_sports_label)
        locationsLabel.text = getString(R.string.trainer_management_locations_label)
        openClassesLabel.text = getString(R.string.trainer_management_open_classes_label)
    }

    private fun showUserData() {
        val userData = SharedApp.preferences.user

        nameTextView.text = "${userData!!.name} ${userData!!.lastname}"
        usertTypeTextView.text = if (userData!!.coach) getString(R.string.trainer) else getString(R.string.athlete)
        if (userData.thumbnail.isNotBlank() && userData.thumbnail.isNotEmpty()) {
            Picasso.get().load(userData.thumbnail).into(avatarImageView)
        }

        showSports(userData!!.activities)
        showLocations(userData!!.locations)
        showOpenClasses(userData!!.classes)

    }

    private fun setUpActivitiesRecycler() {
        activitiesAdapter = ActivityListAdapter { activity -> onSportClicked(activity) }

        activitiesListView.layoutManager = GridLayoutManager(this.activity, 1, GridLayoutManager.HORIZONTAL, false)
        activitiesListView.itemAnimator = DefaultItemAnimator()
        activitiesListView.adapter = activitiesAdapter
    }


    private fun showSports(sports: Array<SportModel>) {
        activitiesAdapter.swapData(sports.toList())
    }

    private fun showLocations(locations: Array<LocationModel>) {
        locationsAdapter = LocationStripViewAdapter(locations.toList(), this.activity!!)
        locationsListview.locationsListview.adapter = locationsAdapter
        locationsListview.locationsListview.layoutParams.height = if (locations.count() == 0) 100 else 124 * locations.count()
    }

    private fun showOpenClasses(classes: Array<ClassModel>) {
        classesAdapter = ClassStripViewAdapter(classes.toList(), this.activity!!)
        openClassesListview.openClassesListview.adapter = classesAdapter
        openClassesListview.openClassesListview.layoutParams.height = if (classes.count() == 0) 100 else 486 * classes.count()
    }

    private fun onSportClicked(activity: SportModel) {
        // TODO
    }
}
