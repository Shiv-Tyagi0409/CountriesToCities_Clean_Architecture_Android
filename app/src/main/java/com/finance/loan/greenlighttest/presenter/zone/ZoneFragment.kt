package com.finance.loan.greenlighttest.presenter.zone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.finance.loan.greenlighttest.R
import com.finance.loan.greenlighttest.databinding.FragmentZoneBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ZoneFragment : Fragment() {

    private var _binding: FragmentZoneBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZoneViewModel by viewModels()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentZoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCurrentProduct()
        observe()
    }

    private fun fetchCurrentProduct(){
        val id = arguments?.getString("country_id") ?: 0
        Log.d("PreviousSelectedCountry2",id.toString())
        viewModel.getData()
    }

    private fun observe() {
        setupRecyclerView()
        viewModel.mZone
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { zone ->
                handleProducts(zone)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun handleProducts(mZone: List<SalesZone>){
        binding.zoneRecyclerView.adapter?.let {
            if(it is ZoneAdapter){
                it.updateList(mZone)
            }
        }
    }


    private fun setupRecyclerView(){
        val mAdapter = ZoneAdapter(mutableListOf())
        mAdapter.setItemTapListener(object : ZoneAdapter.OnItemTap{

            override fun onTap(salesZone: SalesZone) {
                val b = bundleOf("zone_id" to salesZone.territory)
                findNavController().navigate(R.id.regionFragment, b)
            }
        })

        binding.zoneRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }



}