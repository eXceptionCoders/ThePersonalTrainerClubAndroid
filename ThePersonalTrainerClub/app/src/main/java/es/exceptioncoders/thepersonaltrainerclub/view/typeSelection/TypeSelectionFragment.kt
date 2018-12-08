package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_type_selection.*

class TypeSelectionFragment : BaseFragment(), TypeSelectionFragmentContract.View {
    private lateinit var mPresenter: TypeSelectionFragmentContract.Presenter<TypeSelectionFragment>

    override fun bindLayout(): Int = R.layout.fragment_type_selection

    override fun localizeView() {
        titleTextView.text = resources.getString(R.string.type_selection_title_label)
    }
}
