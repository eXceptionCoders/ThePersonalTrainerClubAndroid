package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import android.os.Bundle
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
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityStripViewAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ClassStripViewAdapter
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.LocationStripViewAdapter
import kotlinx.android.synthetic.main.activity_class_list.view.*
import kotlinx.android.synthetic.main.activity_location_list.view.*
import kotlinx.android.synthetic.main.activity_strip_grid.view.*
import kotlinx.android.synthetic.main.fragment_trainer_management.*

class TrainerManagementFragment : BaseFragment(), TrainerManagementFragmentContract.View {
    private lateinit var mPresenter: TrainerManagementFragmentContract.Presenter<TrainerManagementFragment>
    private lateinit var sportsAdapter: ActivityStripViewAdapter
    private lateinit var locationsAdapter: LocationStripViewAdapter
    private lateinit var classesAdapter: ClassStripViewAdapter

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

        nameTextView.text = "${userData.name} ${userData.lastname}"
        usertTypeTextView.text = if (userData.coach) getString(R.string.trainer) else getString(R.string.athlete)
        userData.thumbnail?.let {
            Picasso.get().load(it).into(avatarImageView)
        }

        showSports(userData.activities)
        showLocations(userData.locations)
        showOpenClasses(userData.classes)

    }

    private fun showSports(sports: Array<SportModel>) {
        sportsAdapter = ActivityStripViewAdapter(sports.toList(), emptyArray<SportModel>().toMutableList(), this.activity!!)
        gridview.gridview.adapter = sportsAdapter
    }

    private fun showLocations(locations: Array<LocationModel>) {
        locationsAdapter = LocationStripViewAdapter(locations.toList(), this.activity!!)
        locationsListview.locationsListview.adapter = locationsAdapter
    }

    private fun showOpenClasses(classes: Array<ClassModel>) {
        classesAdapter = ClassStripViewAdapter(classes.toList(), this.activity!!)
        openClassesListview.openClassesListview.adapter = classesAdapter
    }
}
