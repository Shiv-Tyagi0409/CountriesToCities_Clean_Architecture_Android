package com.finance.loan.greenlighttest.presenter.area

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finance.loan.greenlighttest.databinding.ItemProductBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesArea
import com.finance.loan.greenlighttest.presenter.viewmodel.HomeMainFragmentState
import android.widget.Filter
import android.widget.Filterable
import java.util.*
import kotlin.collections.ArrayList

class AreaAdapter (private var Area: MutableList<SalesArea>) :
    RecyclerView.Adapter<AreaAdapter.ViewHolder>() {


    var areaFilterList = ArrayList<SalesArea>()

    init {
        areaFilterList = Area as ArrayList<SalesArea>
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(mArea: List<SalesArea>){
        Area.clear()
        areaFilterList.clear()

        areaFilterList.addAll(mArea)
        notifyDataSetChanged()
    }

    inner class ViewHolder (private val itemBinding: ItemProductBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(salesArea: SalesArea) {

            itemBinding.productNameTextView.text = salesArea.area
            itemBinding.root.setOnClickListener {
               HomeMainFragmentState.ShowToast("${salesArea.area}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaAdapter.ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = areaFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(areaFilterList[position])
    }


    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    areaFilterList = Area as ArrayList<SalesArea>
                } else {
                    val resultList = ArrayList<SalesArea>()
                    for (row in Area) {
                        if (row.area.lowercase().contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    areaFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = areaFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                areaFilterList = results?.values as ArrayList<SalesArea>
                notifyDataSetChanged()
            }

        }
    }
}