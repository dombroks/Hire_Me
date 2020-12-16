package com.dom_broks.hireme.adapter

import android.content.Context
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dom_broks.hireme.R
import com.dom_broks.hireme.model.Job

class JobAdapter(
    private var items: List<Job>,
    private val listener: OnItemClickListener,
    private var ctx: Context
) :
    RecyclerView.Adapter<JobAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_list_item, parent, false);
        return ViewHolder(view);
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.companyName.text = item.Company
        holder.location.text =
            getLocationNameFromLatLng(item.Location?.latitude!!, item.Location!!.longitude!!)
        holder.salary.text = item.Salary
        holder.title.text = item.Title
        holder.experience.text = item.Experience
        holder.type.text = item.Type


        Glide.with(holder.companyImage.context)
            .load(item.Image.toString())
            .apply(RequestOptions.placeholderOf(R.drawable.button_shape))
            .into(holder.companyImage)

        holder.cardView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.itemLayout)
        val title: TextView = itemView.findViewById(R.id.title)
        val companyName: TextView = itemView.findViewById(R.id.company)
        val location: TextView = itemView.findViewById(R.id.location)
        val salary: TextView = itemView.findViewById(R.id.salary)
        val companyImage: ImageView = itemView.findViewById(R.id.image)
        val experience: TextView = itemView.findViewById(R.id.experience)
        val type: TextView = itemView.findViewById(R.id.type)
    }

    fun filter(filteredList: List<Job>) {
        items = filteredList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private fun getLocationNameFromLatLng(lat: Double, lng: Double): String {
        val geocoder = Geocoder(ctx)
        val address = geocoder.getFromLocation(lat, lng, 1)[0]
        return address.getAddressLine(0).toString()
    }
}
