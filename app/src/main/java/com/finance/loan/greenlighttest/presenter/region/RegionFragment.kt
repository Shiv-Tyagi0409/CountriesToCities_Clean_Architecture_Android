package com.finance.loan.greenlighttest.presenter.region

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.finance.loan.greenlighttest.R
import com.finance.loan.greenlighttest.databinding.FragmentRegionBinding
import com.finance.loan.greenlighttest.databinding.FragmentZoneBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesRegion
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone
import com.finance.loan.greenlighttest.presenter.zone.ZoneAdapter
import com.finance.loan.greenlighttest.presenter.zone.ZoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RegionFragment : Fragment() {


    private var _binding: FragmentRegionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCurrentProduct()
        observe()
    }

    private fun fetchCurrentProduct() {

        val id = arguments?.getString("zone_id") ?: 0
        Log.d("PreviousSelectedZone3",id.toString())
        viewModel.getData()
    }

    private fun observe() {
        setupRecyclerView()
        viewModel.mRegion
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { region ->
                handleProducts(region)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun handleProducts(mRegion: List<SalesRegion>){
        binding.regionRecyclerView.adapter?.let {
            if(it is RegionAdapter){
                it.updateList(mRegion)
            }
        }
    }

    private fun setupRecyclerView() {
        val mAdapter = RegionAdapter(mutableListOf())
        mAdapter.setItemTapListener(object : RegionAdapter.OnItemTap{

            override fun onTap(salesRegion: SalesRegion) {
                val b = bundleOf("region_id" to salesRegion.region)
                findNavController().navigate(R.id.areaFragment, b)
            }
        })

        binding.regionRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }


    }


}