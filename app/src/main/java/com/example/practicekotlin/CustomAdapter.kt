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
import kotlinx.android.synthetic.main.list_item.view.*
import retrofit2.Call
import javax.security.auth.callback.Callback


class CustomAdapter(private val context: Context, private var _food: MutableList<Food>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var food: MutableList<Food> = _food
    var onClick: ((Food?)->(Unit))? = null

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

        val feeder = food[position]
        Glide.with(holder.imgViewFood.context).load(feeder.url).error(R.drawable.cherries).into(holder.imgViewFood)
        holder.textViewFoodName.text =  feeder.title ?: "Unkhonws"
        holder.textViewPriceFood.text = feeder.price.toString()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewFoodName: TextView = itemView.textViewFoodName
        val textViewPriceFood: TextView = itemView.textViewPriceFood
        val imgViewFood: ImageView = itemView.imgViewFood

        init {
            itemView.setOnClickListener {
                var position = adapterPosition
                  var item = food[position]
                onClick?.invoke(item)
                //lay item tu mang

                }
            }

        }



}