package com.example.foodtruckapp.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 2,
    entities = [
        FoodTruck::class,
        Customer::class,
    ],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodTruckDao(): FoodTruckDao
    abstract fun customerDao(): CustomerDao

    fun populateFoodTrucks(foodTruckDao: FoodTruckDao) {
        foodTruckDao.deleteAll();
        for(foodTruck in InitialFoodTruckData.data) {
            foodTruckDao.insert(foodTruck)
        }
    }

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
//                    uncomment to reset database
//                    context.deleteDatabase("app-db")
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE!!.populateFoodTrucks(INSTANCE!!.foodTruckDao())
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}