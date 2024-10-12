package com.rwa.luxuryhotel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListHotelAdapter(private val listHotel: ArrayList<Hotel>) : RecyclerView.Adapter<ListHotelAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_card_item_name)
        val tvLocation: TextView = itemView.findViewById(R.id.tv_card_item_location)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_card_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHotel.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, location, photo) = listHotel[position]
        holder.tvName.text = name
        holder.tvLocation.text = location
        Glide.with(holder.itemView.context)
            .load(photo)
            .placeholder(R.drawable.bouncing_circles)
            .into(holder.imgPhoto)
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.KEY_HOTEL, listHotel[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}