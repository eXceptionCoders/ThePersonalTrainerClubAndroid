package es.exceptioncoders.thepersonaltrainerclub.view.addSport

import android.os.Bundle
import android.support.v7.widget.Toolbar
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityStripViewAdapter
import kotlinx.android.synthetic.main.activity_add_sport.*
import kotlinx.android.synthetic.main.activity_strip_grid.view.*

class AddSportActivity : BaseActivity(), AddSportActivityContract.View {
    private lateinit var mPresenter: AddSportActivityContract.Presenter<AddSportActivity>

    override fun bindLayout(): Int = R.layout.activity_add_sport

    override fun localizeView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = AddSportActivityNavigator() as AddSportActivityContract.Navigator<AddSportActivityContract.View>
        mNavigator.attachView(this)

        mPresenter = AddSportActivityPresenter(mNavigator) as AddSportActivityContract.Presenter<AddSportActivity>
        mPresenter.attachView(this)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.add_sport_title)

        mPresenter.create()
    }

    override fun showSports(sports: Array<SportModel>) {
        val sportsAdapter = ActivityStripViewAdapter(sports, SharedApp.preferences.user.activities,this)
        gridview.gridview.adapter = sportsAdapter

        gridview.gridview.setOnItemClickListener { adapterView, view, i, l ->
            //TODO: Check/Uncheck
        }
    }

    override fun showError() {

    }
}
