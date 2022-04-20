package com.example.foodtruckapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodtruckapp.database.FoodTruck

class ListItemAdapter(private val foodTrucks : List<FoodTruck>) : Adapter<ListItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_row, parent, false)

        return ListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder.setFoodTruck(foodTrucks[position])
    }

    override fun getItemCount(): Int {
        return foodTrucks.size
    }
}