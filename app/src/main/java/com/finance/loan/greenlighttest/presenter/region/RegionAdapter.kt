package com.finance.loan.greenlighttest.presenter.region

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finance.loan.greenlighttest.databinding.ItemProductBinding
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesRegion
import com.finance.loan.greenlighttest.domain.mainhome.model.SalesZone
import com.finance.loan.greenlighttest.presenter.zone.ZoneAdapter

class RegionAdapter (private val Region: MutableList<SalesRegion>) :
    RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    interface OnItemTap {
        fun onTap(salesRegion: SalesRegion)
    }

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    private var onTapListener: OnItemTap? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(mZone: List<SalesRegion>){
        Region.clear()
        Region.addAll(mZone)
        notifyDataSetChanged()
    }

    inner class ViewHolder (private val itemBinding: ItemProductBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(salesRegion: SalesRegion) {

            itemBinding.productNameTextView.text = salesRegion.region
            itemBinding.root.setOnClickListener {
                onTapListener?.onTap(salesRegion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionAdapter.ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = Region.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Region[position])
    }
}