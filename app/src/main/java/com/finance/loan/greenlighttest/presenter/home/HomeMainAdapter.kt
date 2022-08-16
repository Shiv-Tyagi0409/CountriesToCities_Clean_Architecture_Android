package com.finance.loan.greenlighttest.presenter.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finance.loan.greenlighttest.databinding.ItemProductBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesCountry

class HomeMainAdapter (private val Country: MutableList<SalesCountry>): RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    interface OnItemTap {
        fun onTap(salesCountry: SalesCountry)
    }

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    private var onTapListener: OnItemTap? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(mCountry: List<SalesCountry>){
        Country.clear()
        Country.addAll(mCountry)
        notifyDataSetChanged()
    }


   inner class ViewHolder(private val itemBinding: ItemProductBinding) : RecyclerView.ViewHolder(itemBinding.root) {

       fun bind(salesCountry: SalesCountry) {

           itemBinding.productNameTextView.text = salesCountry.territory
           itemBinding.root.setOnClickListener {
               onTapListener?.onTap(salesCountry)
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(Country[position])

    override fun getItemCount() = Country.size


}