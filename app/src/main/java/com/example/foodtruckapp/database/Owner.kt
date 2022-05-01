package com.example.foodtruckapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owners")

data class Owner(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "plain_password") val password: String?,
    @ColumnInfo(name = "food_truck_id") val foodTruckId: Long,
) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}
