package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import android.os.Bundle
import android.view.View
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_type_selection.*

class TypeSelectionFragment : BaseFragment(), TypeSelectionFragmentContract.TypeSelectionView {
    override fun bindLayout(): Int = R.layout.fragment_type_selection

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTextView.text = resources.getString(R.string.type_selection_title_label)
    }

    override fun localizeView() {
        //titleTextView.text = resources.getString(R.string.type_selection_title_label)
    }
}
