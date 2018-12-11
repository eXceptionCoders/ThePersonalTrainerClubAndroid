package es.exceptioncoders.thepersonaltrainerclub.view.dashboard

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement.TrainerManagementFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity(), DashboardActivityContract.View {
    private lateinit var mPresenter: DashboardActivityContract.Presenter<DashboardActivity>

    override fun bindLayout(): Int = R.layout.activity_dashboard

    private lateinit var mMenu: Menu

    override fun localizeView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = DashboardActivityNavigator() as DashboardActivityContract.Navigator<DashboardActivityContract.View>
        mNavigator.attachView(this)

        mPresenter = DashboardActivityPresenter(mNavigator) as DashboardActivityContract.Presenter<DashboardActivity>
        mPresenter.attachView(this)

        val fragmentAdapter = DashboardPagerAdapter(supportFragmentManager, this)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        //for (i in 0 until tabs_main.tabCount) {
        //    tabs_main.getTabAt(0)!!.icon = resources.getDrawable(R.drawable.ic_task_list)
        //}

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.app_bar_name)

        tabs_main.addOnTabSelectedListener(object :  TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    setActionBarMenu(it.position)
                    if (it.position < supportFragmentManager.fragments.size)
                        (supportFragmentManager.fragments[it.position] as BaseFragment).localizeView()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mMenu = menu
        menuInflater.inflate(R.menu.menu_dashboard, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        return when (id) {
            R.id.action_activities -> {
                mPresenter.onAddActivitiesTapped()
                true
            }
            R.id.action_locations -> {
                mPresenter.onAddLocationsTapped()
                true
            }
            R.id.action_logout -> {
                mPresenter.onLogoutTapped()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setActionBarMenu(position: Int) {
        mMenu.clear()
        when (position) {
            0 -> menuInflater.inflate(R.menu.menu_dashboard, mMenu)
            else -> menuInflater.inflate(R.menu.menu_general, mMenu)
        }
    }
}
