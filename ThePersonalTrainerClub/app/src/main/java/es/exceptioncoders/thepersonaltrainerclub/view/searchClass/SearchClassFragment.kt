package es.exceptioncoders.thepersonaltrainerclub.view.searchClass

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment

class SearchClassFragment : BaseFragment(), SearchClassFragmentContract.View {
    override fun bindLayout(): Int = R.layout.fragment_search_class

    override fun localizeView() {
    }

}