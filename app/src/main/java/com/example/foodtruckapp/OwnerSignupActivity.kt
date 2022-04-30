package com.example.foodtruckapp

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.Customer
import com.example.foodtruckapp.database.FoodTruck
import com.example.foodtruckapp.databinding.ActivityOwnerSignupBinding
import java.util.*


class OwnerSignupActivity : AppCompatActivity(){

    private lateinit var binding: ActivityOwnerSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_signup)
        binding = ActivityOwnerSignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupToolbar()

        loginExistingCustomer()
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun loginExistingCustomer() {
        val database = AppDatabase.getInstance(this)
        val customers = database?.customerDao()?.getAll()

        if (customers != null && customers.isNotEmpty()) {
            val loggedInCustomer = customers.last()
            showToast("Logged in ${loggedInCustomer.name}")

            openLocationActivity()
        }
    }

    fun showToast(message: String) {
        val myToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        myToast.show()
    }

    fun checkOwnerInfo(): Boolean {
        if (binding.userInputFoodTruckName.text.toString().isEmpty()) {
            showToast("Enter a foodtruck name")
            return false;
        }
        if (binding.userInputEmail.text.toString().isEmpty()) {
            showToast("Enter an email")
            return false;
        }
        if (binding.userInputPassword.text.toString().isEmpty()) {
            showToast("Enter a password")
            return false;
        }
        val addressInput: String = binding.truckLocation.text.toString()
        if (addressInput.isEmpty()) {
            showToast("Enter an address")
            return false;
        }

        val address = findAddress(addressInput)
        if (address == null ||
                !address.hasLatitude() ||
                !address.hasLatitude()) {
            showToast("Address not found")
            return false
        }

        return true;
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

    fun createOwner() {
        if (!checkOwnerInfo()) {
            return
        }

        val newCustomer = Customer(
            0,
            binding.userInputFoodTruckName.text.toString(),
            binding.userInputEmail.text.toString(),
            binding.userInputPassword.text.toString(),
        )

        val addressInput = binding.truckLocation.text.toString()
        val address = findAddress(addressInput)
        val newFoodTruck = FoodTruck(
            0,
            binding.userInputFoodTruckName.text.toString(),
            address!!.latitude,
            address!!.longitude,
            "TBD",
            addressInput,
            5.0,
            true,
            null,
        )

        val database = AppDatabase.getInstance(this)
        if (database != null) {
            database.customerDao().insert(newCustomer)
            database.foodTruckDao().insert(newFoodTruck)

            openLocationActivity()
        }
    }

    fun sendData(view: View) {
        createOwner()
    }

    fun openLocationActivity() {
        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
    }
}