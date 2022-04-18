package com.example.foodtruckapp.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodTruckDao {
    @Query("SELECT * FROM food_trucks")
    fun getAll(): List<FoodTruck>
}