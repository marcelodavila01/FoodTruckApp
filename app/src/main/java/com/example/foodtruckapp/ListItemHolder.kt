package com.example.foodtruckapp

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruckapp.database.FoodTruck

class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private var view: View = view
    private var foodTruck: FoodTruck? = null

    init {
        view.setOnClickListener(this)
    }

    fun setFoodTruck(foodTruck: FoodTruck) {
        this.foodTruck = foodTruck

        val nameView = view.findViewById<TextView>(R.id.item_name)
        nameView.text = foodTruck.name

        val addressView = view.findViewById<TextView>(R.id.item_address)
        addressView.text = foodTruck.address

        val hoursView = view.findViewById<TextView>(R.id.item_hours)
        hoursView.text = foodTruck.hours

        val ratingView = view.findViewById<TextView>(R.id.item_rating)
        ratingView.text = "${foodTruck.rating.toString()} / 5.0"
    }

    override fun onClick(p0: View?) {
        val intent = Intent(view.context, FoodTruckDetailActivity::class.java)

        intent.putExtra("name", foodTruck?.name)
        intent.putExtra("hours", foodTruck?.hours)
        intent.putExtra("address", foodTruck?.address)
        intent.putExtra("rating", foodTruck?.rating)

        view.context.startActivity(intent)
    }

    companion object {
        private val FOOD_TRUCK_KEY = "FOOD_TRUCK_KEY"
    }
}