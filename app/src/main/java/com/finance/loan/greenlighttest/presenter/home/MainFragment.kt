package com.finance.loan.greenlighttest.presenter.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.finance.loan.greenlighttest.R
import com.finance.loan.greenlighttest.data.utils.NetworkStatus
import com.finance.loan.greenlighttest.data.utils.NetworkStatusHelper
import com.finance.loan.greenlighttest.databinding.FragmentMainBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.Place
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesCountry
import com.finance.loan.greenlighttest.presenter.viewmodel.HomeMainFragmentState
import com.finance.loan.greenlighttest.presenter.viewmodel.SharedViewModel
import com.ydhnwb.cleanarchitectureexercise.presentation.common.extension.gone
import com.ydhnwb.cleanarchitectureexercise.presentation.common.extension.showGenericAlertDialog
import com.ydhnwb.cleanarchitectureexercise.presentation.common.extension.showToast
import com.ydhnwb.cleanarchitectureexercise.presentation.common.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by viewModels()

    private var connectionStatus: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewModel.getData()

    }


    private fun setupRecyclerView(){
        val mAdapter = HomeMainAdapter(mutableListOf())
        mAdapter.setItemTapListener(object : HomeMainAdapter.OnItemTap{
            override fun onTap(salesCountry: SalesCountry) {
                val b = bundleOf("country_id" to salesCountry.territory)
                findNavController().navigate(R.id.ZoneFragment, b)
            }
        })

        binding.countryRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun handleState(state: HomeMainFragmentState){
        when(state){
            is HomeMainFragmentState.Init -> Unit
            is HomeMainFragmentState.ErrorLogin -> handleErrorLogin(state.rawResponse)
            is HomeMainFragmentState.SuccessLogin -> handleSuccessLogin(state.loginResponse)
            is HomeMainFragmentState.ShowToast ->  requireActivity().showToast(state.message)
            is HomeMainFragmentState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleSuccessLogin(Response: Place) {


        Log.d("Checkingdata",Response.toString())

        setupRecyclerView()

        handleProducts(Response.ResponseData.sales_country)
    }

    private fun handleErrorLogin(rawResponse: String) {

        Log.d("ErrorLogin",rawResponse.toString())
        requireActivity().showGenericAlertDialog("Getting last updated data from local source")
        observeData()
    }

    private fun handleLoading(isLoading: Boolean){
        if(isLoading){
            binding.loadingProgressBar.visible()
        }else{
            binding.loadingProgressBar.gone()
        }
    }

    private fun observe() {
        observeState()

    }

    private fun observeData() {
        setupRecyclerView()
        viewModel.mCountry
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { country ->
                handleProducts(country)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleProducts(mCountry: List<SalesCountry>){
        binding.countryRecyclerView.adapter?.let {
            if(it is HomeMainAdapter){
                it.updateList(mCountry)
            }
        }
    }

    private fun observeState() {
        viewModel.mState.flowWithLifecycle(viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun checkConnection() {
        NetworkStatusHelper(requireContext()).observe(viewLifecycleOwner) {
            connectionStatus = it == NetworkStatus.Available
            if (it == NetworkStatus.Available){
                connectionStatus = true
            } else {
                connectionStatus = false
                Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}