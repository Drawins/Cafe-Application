package com.example.cafeapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Date

class FoodAdapter(private val context: Context, private val foodList: MutableList<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.bind(currentFood)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.discriptionTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(food: Food) {
            nameTextView.text = food.name
            descriptionTextView.text = food.description
            amountTextView.text = "$${food.price}"

            // Load image using Glide or any other image loading library
            Glide.with(context)
                .load(food.imageUrl)
                .into(imageView)
        }
    }
}