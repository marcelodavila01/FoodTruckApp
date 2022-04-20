package com.example.foodtruckapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers")
    fun getAll(): List<Customer>

    @Query("DELETE FROM customers")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(customer: Customer)

    @Query("SELECT * FROM customers ORDER BY ID DESC LIMIT 1")
    fun getLast(): Customer
}