package com.example.practicekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicekotlin.Model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class CustomAdapter(private val context: Context, private val food: MutableList<Food>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = food.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Picasso.get().load(food[position].url.into(holder.imgViewFood))
        Picasso.with(holder.imgViewFood.context).load(food[position].url).into(holder.imgViewFood)
        holder.textViewFoodName.text =  food[position].title
        holder.textViewPriceFood.text = food[position].price.toString()


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewFoodName : TextView = itemView.textViewFoodName
        val textViewPriceFood : TextView = itemView.textViewPriceFood
        val imgViewFood : ImageView = itemView.imgViewFood


    }


}