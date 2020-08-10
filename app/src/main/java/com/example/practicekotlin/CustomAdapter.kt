package com.example.practicekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.line_food.view.*
import kotlinx.android.synthetic.main.line_food.view.imgViewFood
import kotlinx.android.synthetic.main.line_food.view.textViewFoodName
import kotlinx.android.synthetic.main.line_food.view.textViewPriceFood
import kotlinx.android.synthetic.main.list_item.view.*

class CustomAdapter(private val food: List<Food>) : RecyclerView.Adapter<CustomAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return viewHolder(itemView)
    }

    override fun getItemCount() = food.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = food[position]

        holder.imgViewFood.setImageResource(currentItem.imgFood)
        holder.textViewFoodName.text =  currentItem.nameFood
        holder.textViewPriceFood.text = currentItem.priceFood.toString()


    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewFoodName : TextView = itemView.textViewFoodName
        val textViewPriceFood : TextView = itemView.textViewPriceFood
        val imgViewFood : ImageView = itemView.imgViewFood


    }


}