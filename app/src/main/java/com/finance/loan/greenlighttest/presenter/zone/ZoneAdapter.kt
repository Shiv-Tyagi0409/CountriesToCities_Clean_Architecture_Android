package com.finance.loan.greenlighttest.presenter.zone

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finance.loan.greenlighttest.databinding.ItemProductBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesCountry
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone
import com.finance.loan.greenlighttest.presenter.home.HomeMainAdapter

class ZoneAdapter (private val Zone: MutableList<SalesZone>) : RecyclerView.Adapter<ZoneAdapter.ViewHolder>(){

    interface OnItemTap {
        fun onTap(salesZone: SalesZone)
    }

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    private var onTapListener: OnItemTap? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(mZone: List<SalesZone>){
        Zone.clear()
        Zone.addAll(mZone)
        notifyDataSetChanged()
    }

    inner class ViewHolder (private val itemBinding: ItemProductBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(salesZone: SalesZone) {

            itemBinding.productNameTextView.text = salesZone.zone
            itemBinding.root.setOnClickListener {
                onTapListener?.onTap(salesZone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneAdapter.ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Zone[position])
    }

    override fun getItemCount() = Zone.size
}