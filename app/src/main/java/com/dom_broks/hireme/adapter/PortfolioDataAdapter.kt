package com.dom_broks.hireme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dom_broks.hireme.R
import com.dom_broks.hireme.model.PortfolioItem

class PortfolioDataAdapter(
    private val items: List<PortfolioItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<PortfolioDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.portfolio_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemTitle.text = item.Title

        Glide.with(holder.itemImage.context)
            .load(item.Image.toString())
            .into(holder.itemImage);
        holder.deleteButton.setOnClickListener {
            listener.onItemDelete(item, position)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        val deleteButton: Button = itemView.findViewById(R.id.deletePortfolioItem)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemDelete(item : PortfolioItem, position: Int)
    }


}

