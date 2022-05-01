package com.example.foodtruckapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OwnerDao {
    @Query("SELECT * FROM owners")
    fun getAll(): List<Owner>

    @Query("DELETE FROM owners")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(owner: Owner)

    @Query("SELECT * FROM owners ORDER BY ID DESC LIMIT 1")
    fun getLast(): Owner

    @Query("SELECT * FROM owners WHERE email = :email AND plain_password = :password")
    fun getByEmailAndPassword(email: String, password: String): Owner?

    @Query("SELECT * FROM owners WHERE email = :email")
    fun getByEmail(email: String): Owner?

    @Query("DELETE FROM owners WHERE id = :id")
    fun deleteById(id: Long)
}