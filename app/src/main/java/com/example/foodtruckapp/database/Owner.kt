package com.example.foodtruckapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owners")

data class Owner(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "plain_password") val password: String?,
) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}
