package com.dom_broks.hireme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dom_broks.hireme.R
import com.dom_broks.hireme.model.Experience

class ExperienceDataAdapter(private val items: List<Experience>) :
    RecyclerView.Adapter<ExperienceDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.experience_list_item, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.companyName.text = item.Place
        holder.duration.text = item.Duration
        holder.fromTo.text = item.From + " - " + item.To
        holder.title.text = item.Title


        Glide.with(holder.companyImage.context)
            .load(item.Image.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.button_shape))
            .into(holder.companyImage);


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val companyName: TextView = itemView.findViewById(R.id.company)
        val duration: TextView = itemView.findViewById(R.id.duration)
        val fromTo: TextView = itemView.findViewById(R.id.from_to)
        val companyImage: ImageView = itemView.findViewById(R.id.companyImage)
    }

    fun getItemIdAt(position: Int): String{
        val item = items[position]
        return item.Id.toString()
    }

}