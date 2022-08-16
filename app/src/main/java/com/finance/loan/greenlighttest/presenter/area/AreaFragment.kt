package com.finance.loan.greenlighttest.presenter.area

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import com.finance.loan.greenlighttest.R
import com.finance.loan.greenlighttest.databinding.FragmentAreaBinding

import com.finance.loan.greenlighttest.domain.mainhome.model.SalesArea
import com.finance.loan.greenlighttest.presenter.viewmodel.HomeMainFragmentState

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AreaFragment : Fragment() {
    private var _binding: FragmentAreaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AreaViewModel by viewModels()
    private var regionCheck : String = ""
    private val mAdapter = AreaAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countrySearch = view.findViewById<SearchView>(R.id.area_search)
        val searchIcon =
            countrySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLUE)

        val cancelIcon =
            countrySearch.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.RED)

        val textView = countrySearch.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        textView.setTextColor(Color.GREEN)
        // If you want to change the color of the cursor, change the 'colorAccent' in colors.xml

        countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.getFilter().filter(newText)
                return false
            }

        })


        viewModel.getData()
        observe()
        fetchCurrentProduct()
    }

    private fun fetchCurrentProduct() {

        regionCheck = arguments?.getString("region_id") ?: ""
        Log.d("PreviousSelectedZone4",regionCheck.toString())
        viewModel.getData()
    }

    private fun observe() {
        setupRecyclerView()
        viewModel.mArea
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { area ->
                handleProducts(area)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun handleProducts(area: List<SalesArea>) {

        val newArrayList=ArrayList<SalesArea>()
        //using for loop
        newArrayList.clear()
        for(item in area){
            if(item.territory.contains(regionCheck.uppercase())){
                newArrayList.add(item)
            }else{
                Log.i("Check_Duplicate_values","-- ${item}")
            }
        }
        Log.i("final_List","-- ${newArrayList}")


        binding.areaRecyclerView.adapter?.let {
            if(it is AreaAdapter){
                it.updateList(newArrayList)
            }
        }
    }

    private fun setupRecyclerView() {

        binding.areaRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

    }




}