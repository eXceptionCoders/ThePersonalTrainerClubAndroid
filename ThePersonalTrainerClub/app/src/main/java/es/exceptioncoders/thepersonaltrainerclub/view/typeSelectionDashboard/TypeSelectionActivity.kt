package es.exceptioncoders.thepersonaltrainerclub.view.typeSelectionDashboard

import android.os.Bundle
import android.support.v7.widget.Toolbar
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_type_selection.*

class TypeSelectionActivity : BaseActivity(), TypeSelectionActivityContract.TypeSelectionView {
    private lateinit var mPresenter: TypeSelectionActivityContract.TypeSelectionViewPresenter<TypeSelectionActivity>

    override fun bindLayout(): Int = R.layout.activity_type_selection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = TypeSelectionActivityNavigator() as TypeSelectionActivityContract.TypeSelectionViewNavigator<TypeSelectionActivityContract.TypeSelectionView>
        mNavigator.attachView(this)

        mPresenter = TypeSelectionActivityPresenter(mNavigator) as TypeSelectionActivityContract.TypeSelectionViewPresenter<TypeSelectionActivity>
        mPresenter.attachView(this)

        imageTrainer.setOnClickListener {
            mPresenter.onTypeSelected(TypeSelection.Trainer)
        }

        imageClient.setOnClickListener {
            mPresenter.onTypeSelected(TypeSelection.Client)
        }

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
    }

    override fun localizeView() {
        supportActionBar?.title = resources.getString(R.string.type_selection_title)
        titleTextView.text = resources.getString(R.string.type_selection_title_label)
    }
}
