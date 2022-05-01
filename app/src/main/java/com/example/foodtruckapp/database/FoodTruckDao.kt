package com.example.foodtruckapp.database

import androidx.room.*

@Dao
interface FoodTruckDao {
    @Query("SELECT * FROM food_trucks")
    fun getAll(): List<FoodTruck>

    @Query("DELETE FROM food_trucks")
    fun deleteAll()

    @Query("DELETE FROM food_trucks WHERE owner_added != 1")
    fun deleteAllInitial()

    @Query("DELETE FROM food_trucks WHERE owner_added = 1")
    fun deleteOwner()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(foodTruck: FoodTruck): Long

    @Query("SELECT * FROM food_trucks WHERE id = :id")
    fun getById(id: Long): FoodTruck?

    @Update(entity = FoodTruck::class)
    fun update(obj: FoodTruck)

    @Query("DELETE FROM food_trucks WHERE id = :id")
    fun deleteById(id: Long)
}