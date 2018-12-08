package es.exceptioncoders.thepersonaltrainerclub.view.addLocation

import android.os.Bundle
import android.support.v7.widget.Toolbar
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity

class AddLocationActivity : BaseActivity(), AddLocationActivityContract.View {

    private lateinit var mPresenter: AddLocationActivityContract.Presenter<AddLocationActivity>

    override fun bindLayout(): Int = R.layout.activity_add_location

    override fun localizeView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = AddLocationActivityNavigator() as AddLocationActivityContract.Navigator<AddLocationActivityContract.View>
        mNavigator.attachView(this)

        mPresenter = AddLocationActivityPresenter(mNavigator) as AddLocationActivityContract.Presenter<AddLocationActivity>
        mPresenter.attachView(this)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.add_sport_title)
    }
}
