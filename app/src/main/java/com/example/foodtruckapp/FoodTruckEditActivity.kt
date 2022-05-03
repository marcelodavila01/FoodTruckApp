package com.example.foodtruckapp

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.CurrentLogin
import com.example.foodtruckapp.database.FoodTruck
import com.example.foodtruckapp.database.Owner
import com.example.foodtruckapp.databinding.ActivityFoodTruckEditBinding
import java.util.*

class FoodTruckEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodTruckEditBinding
    private lateinit var database: AppDatabase

    private var owner: Owner? = null
    private var foodTruck: FoodTruck? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_truck_edit)

        binding = ActivityFoodTruckEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = AppDatabase.getInstance(this)!!

        setupToolbar()

        owner = CurrentLogin.getCurrentOwner()!!
        foodTruck = database.foodTruckDao().getById(owner!!.foodTruckId)
        binding.userInputFoodTruckName.setText(foodTruck!!.name)
        binding.userInputHours.setText(foodTruck!!.hours)
        binding.truckLocation.setText(foodTruck!!.address)
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            //user click back
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        val myToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        myToast.show()
    }

    fun findAddress(input: String): Address? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(input, 1)
        if (addresses.size == 0 || !addresses[0].hasLatitude() || !addresses[0].hasLatitude()) {
            showToast("Address not found")
        }

        if (addresses.size > 0) {
            return addresses[0]
        }

        return null
    }

    private fun checkFoodTruckInfo(): Boolean {
        if (binding.userInputFoodTruckName.text.toString().isEmpty()) {
            showToast("Enter a foodtruck name")
            return false
        }

        if (binding.userInputHours.text.toString().isEmpty()) {
            showToast("Enter your hours")
            return false
        }

        val addressInput: String = binding.truckLocation.text.toString()
        if (addressInput.isEmpty()) {
            showToast("Enter an address")
            return false
        }

        val address = findAddress(addressInput)
        if (address == null ||
            !address.hasLatitude() ||
            !address.hasLatitude()) {
            showToast("Address not found")
            return false
        }

        return true
    }

    fun onSave(view: View) {
        if (!checkFoodTruckInfo()) {
            return
        }

        val addressInput = binding.truckLocation.text.toString()
        val address = findAddress(addressInput)!!
        val newFoodTruck = FoodTruck(
            binding.userInputFoodTruckName.text.toString(),
            address.latitude,
            address.longitude,
            binding.userInputHours.text.toString(),
            addressInput,
            5.0,
            true,
            null,
        )

        if (foodTruck != null) {
            newFoodTruck.id = foodTruck?.id
            database.foodTruckDao().update(newFoodTruck)
        } else {
            database.foodTruckDao().insert(newFoodTruck)
        }

        showToast("Your changes have been saved")

        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
    }

    fun onDelete(view: View) {
        database.foodTruckDao().deleteById(foodTruck!!.id!!)
        database.ownerDao().deleteById(owner!!.id!!)
        CurrentLogin.logoutOwner()
        CurrentLogin.seenAnimation = false
        showToast("Your food truck has been deleted")
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }
}