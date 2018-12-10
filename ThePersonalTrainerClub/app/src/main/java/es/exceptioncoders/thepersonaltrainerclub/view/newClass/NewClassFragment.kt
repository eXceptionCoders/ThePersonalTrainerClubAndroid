package es.exceptioncoders.thepersonaltrainerclub.view.newClass

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment

class NewClassFragment : BaseFragment(), NewClassFragmentContract.View {
    private lateinit var mPresenter: NewClassFragmentContract.Presenter<NewClassFragment>

    override fun bindLayout(): Int = R.layout.fragment_new_class

    override fun localizeView() {}
}
