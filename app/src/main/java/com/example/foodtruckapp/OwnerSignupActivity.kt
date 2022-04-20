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
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

        val addresses = findAddress(addressInput)
        if (addresses == null ||
                addresses.size == 0 ||
                !addresses[0].hasLatitude() ||
                !addresses[0].hasLatitude()) {
            showToast("Address not found")
            return false
        }

        return true;
    }

    fun findAddress(input: String): MutableList<Address>? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(input, 1)
        if (addresses.size == 0 || !addresses[0].hasLatitude() || !addresses[0].hasLatitude()) {
            showToast("Address not found")
        }

        return addresses
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

        val database = AppDatabase.getInstance(this)
        if (database != null) {
            database.customerDao().insert(newCustomer)

            openLocationActivity()
        }
    }

    fun sendData(view: View) {
        createOwner()
    }

    fun openLocationActivity() {
        val intent = Intent(this, LocationActivity::class.java).apply{
            putExtra("fullName", binding.userInputFoodTruckName.text.toString())
        }
        startActivity(intent)
    }
}