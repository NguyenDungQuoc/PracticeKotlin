package com.example.practicekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicekotlin.Model.Food
import com.example.practicekotlin.Model.FoodModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class CustomAdapter(private val context: Context, private var _food: MutableList<Food>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var food: MutableList<Food> = _food

    fun setData(food: MutableList<Food>) {
        this.food = food
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = food.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Picasso.get().load(food[position].url.into(holder.imgViewFood))
        val feeder = food[position]
        Glide.with(holder.imgViewFood.context).load(feeder.url).error(R.drawable.cherries).into(holder.imgViewFood)
        holder.textViewFoodName.text =  feeder.title ?: "Unkhonws"
        holder.textViewPriceFood.text = feeder.price.toString()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewFoodName: TextView = itemView.textViewFoodName
        val textViewPriceFood: TextView = itemView.textViewPriceFood
        val imgViewFood: ImageView = itemView.imgViewFood

//        fun setData(item: Food) {
//            Picasso.with(itemView.imgViewFood.context).load(item.url).into(itemView.imgViewFood)
//            itemView.textViewFoodName.text =  item.title
//            itemView.textViewPriceFood.text = item.price.toString()
//        }

    }


}