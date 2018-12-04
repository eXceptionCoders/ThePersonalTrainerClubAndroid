package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import android.os.Bundle
import android.view.View
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_trainer_management.*

class TrainerManagementActivity: BaseActivity(), TrainerManagementActivityContract.TrainerManagementView {


    private lateinit var mPresenter: TrainerManagementActivityContract.TrainerManagementViewPresenter<TrainerManagementActivity>

    override fun bindLayout(): Int = R.layout.activity_trainer_management

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = TrainerManagementActivityNavigator() as TrainerManagementActivityContract.TrainerManagementViewNavigator<TrainerManagementActivityContract.TrainerManagementView>
        mNavigator.attachView(this)

        mPresenter = TrainerManagementActivityPresenter(mNavigator) as TrainerManagementActivityContract.TrainerManagementViewPresenter<TrainerManagementActivity>
        mPresenter.attachView(this)

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun localizeView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}