package com.example.foodtruckapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodTruckDao {
    @Query("SELECT * FROM food_trucks")
    fun getAll(): List<FoodTruck>

    @Query("DELETE FROM food_trucks")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(foodTruck: FoodTruck)
}