package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import android.os.Bundle
import android.widget.ImageView
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_type_selection.*

class TypeSelectionFragment : BaseFragment(), TypeSelectionFragmentContract.View {
    private lateinit var mPresenter: TypeSelectionFragmentContract.Presenter<TypeSelectionFragment>

    override fun bindLayout(): Int = R.layout.fragment_type_selection

    override fun localizeView() {
        titleTextView.text = resources.getString(R.string.type_selection_title_label)

        SharedApp.preferences.user?.let {
            if (it.showCoachView) {
                imageTrainer.scaleX = 1F
                imageTrainer.scaleY = 1F
                imageTrainer.alpha = 0.25F

                imageClient.scaleX = 1.2F
                imageClient.scaleY = 1.2F
                imageClient.alpha = 1F
            }
            else {
                imageTrainer.scaleX = 1.2F
                imageTrainer.scaleY = 1.2F
                imageTrainer.alpha = 1F

                imageClient.scaleX = 1F
                imageClient.scaleY = 1F
                imageClient.alpha = 0.25F
            }

            imageTrainer.isEnabled = !it.showCoachView
            imageClient.isEnabled = it.showCoachView
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mNavigator = TypeSelectionFragmentNavigator() as TypeSelectionFragmentContract.Navigator<TypeSelectionFragmentContract.View>
        mNavigator.attachView(this)

        mPresenter = TypeSelectionFragmentPresenter(mNavigator) as TypeSelectionFragmentContract.Presenter<TypeSelectionFragment>
        mPresenter.attachView(this)

        (imageClient as ImageView).setOnClickListener {
            mPresenter.onTypeSelected(TypeSelection.Client)
        }

        (imageTrainer as ImageView).setOnClickListener {
            mPresenter.onTypeSelected(TypeSelection.Trainer)
        }
    }
}
