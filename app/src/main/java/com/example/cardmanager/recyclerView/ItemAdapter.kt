package com.example.cardmanager.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardmanager.R
import com.google.android.material.imageview.ShapeableImageView

class ItemAdapter(private val dataset: ArrayList<CreditCard>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardImage : ShapeableImageView = view.findViewById(R.id.credit_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.itemView.elevation = 30.0f * position
        holder.itemView.hasOverlappingRendering
        holder.cardImage.setImageResource(item.cardImg)
    }

    override fun getItemCount() = dataset.size
}