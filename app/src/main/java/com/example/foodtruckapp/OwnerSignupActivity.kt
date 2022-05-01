package com.example.foodtruckapp

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.database.*
import com.example.foodtruckapp.databinding.ActivityOwnerSignupBinding
import java.util.*


class OwnerSignupActivity : AppCompatActivity(){

    private lateinit var binding: ActivityOwnerSignupBinding
    private  lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_signup)
        binding = ActivityOwnerSignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = AppDatabase.getInstance(this)!!

        setupToolbar()

        loginExistingOwner()
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_log_in -> {
            //user click back
            val intent = Intent(this, OwnerLoginActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun loginExistingOwner() {
        val currentOwner = CurrentLogin.getCurrentOwner()

        if (currentOwner != null) {
            showToast("Logged in ${currentOwner.name}")

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
            return false
        }
        val emailInput = binding.userInputEmail.text.toString()
        if (emailInput.isEmpty()) {
            showToast("Enter an email")
            return false
        }
        if (binding.userInputPassword.text.toString().isEmpty()) {
            showToast("Enter a password")
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

        if (database.ownerDao().getByEmail(emailInput) != null) {
            showToast("Email already exists")
            return false
        }

        return true
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

        val newOwner = Owner(
            binding.userInputFoodTruckName.text.toString(),
            binding.userInputEmail.text.toString(),
            binding.userInputPassword.text.toString(),
        )

        val addressInput = binding.truckLocation.text.toString()
        val address = findAddress(addressInput)!!
        val newFoodTruck = FoodTruck(
            binding.userInputFoodTruckName.text.toString(),
            address.latitude,
            address.longitude,
            "TBD",
            addressInput,
            5.0,
            true,
            null,
        )

        database.ownerDao().insert(newOwner)
        CurrentLogin.loginOwner(newOwner)
        database.foodTruckDao().insert(newFoodTruck)

        openLocationActivity()
    }

    fun sendData(view: View) {
        createOwner()
    }

    fun openLocationActivity() {
        val intent = Intent(this, LocationActivity::class.java)
        CurrentLogin.isOwner = true
        startActivity(intent)
    }
}