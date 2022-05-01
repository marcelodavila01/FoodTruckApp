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

    @Query("SELECT * FROM customers WHERE email = :email AND plain_password = :password")
    fun getByEmailAndPassword(email: String, password: String): Customer?

    @Query("SELECT * FROM customers WHERE email = :email")
    fun getByEmail(email: String): Customer?
}