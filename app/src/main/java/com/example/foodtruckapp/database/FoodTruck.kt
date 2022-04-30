package com.example.foodtruckapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_trucks")

data class FoodTruck(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "hours") val hours: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "rating") val rating: Double?,
    @ColumnInfo(name = "owner_added") val ownerAdded: Boolean?,
    @ColumnInfo(name = "image_name") val imageName: String?,
)