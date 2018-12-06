package es.exceptioncoders.thepersonaltrainerclub.view.trainerManagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment

class TrainerManagementFragment : BaseFragment() {
    override fun bindLayout(): Int = R.layout.fragment_trainer_management

    override fun localizeView() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_trainer_management, container, false)
    }
}
