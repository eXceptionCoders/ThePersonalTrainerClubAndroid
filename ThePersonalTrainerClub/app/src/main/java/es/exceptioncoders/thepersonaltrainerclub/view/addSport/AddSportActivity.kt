package es.exceptioncoders.thepersonaltrainerclub.view.addSport

import android.os.Bundle
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView.ActivityStripViewAdapter
import kotlinx.android.synthetic.main.activity_strip_grid.*


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

        //val sportsAdapter = ActivityStripViewAdapter(listOf<String>().toTypedArray(), this)
        //gridview.adapter = sportsAdapter
    }

    override fun showSports(sports: Array<SportModel>) {

    }

    override fun showError() {

    }
}
