package es.exceptioncoders.thepersonaltrainerclub.view.addSport

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityStripViewAdapter
import kotlinx.android.synthetic.main.activity_add_sport.*
import kotlinx.android.synthetic.main.activity_strip_grid.view.*

class AddSportActivity : BaseActivity(), AddSportActivityContract.View {
    private lateinit var mPresenter: AddSportActivityContract.Presenter<AddSportActivity>
    private lateinit var sportsAdapter: ActivityStripViewAdapter

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        return if (id == R.id.action_save) {
            mPresenter.saveSports(sportsAdapter.getPreselectedSports())
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun showSports(sports: Array<SportModel>) {
        sportsAdapter = ActivityStripViewAdapter(sports.toList(), SharedApp.preferences.user!!.activities.toMutableList(),this)
        gridview.gridview.adapter = sportsAdapter

        gridview.gridview.setOnItemClickListener { adapterView, view, i, l ->
            sportsAdapter.sportSelected(i)
            sportsAdapter.notifyDataSetChanged()
        }
    }

    override fun showError() {

    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }
}
