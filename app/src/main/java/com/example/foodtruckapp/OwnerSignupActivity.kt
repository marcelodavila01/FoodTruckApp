package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.Customer
import com.example.foodtruckapp.databinding.ActivityOwnerSignupBinding

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
            val myToast = Toast.makeText(this, "Logged in ${loggedInCustomer.name}", Toast.LENGTH_LONG)
            myToast.show()

            openLocationActivity()
        }
    }

    fun createCustomer() {
        val newCustomer = Customer(
            0,
            binding.userInputFullName.text.toString(),
            binding.userInputEmail.text.toString(),
            binding.userInputPassword.text.toString(),
        )

        val database = AppDatabase.getInstance(this)
        if (database != null) {
            database.customerDao().insert(newCustomer)
        }
    }

    fun sendData(view: View) {
        createCustomer()
        openLocationActivity()
    }

    fun openLocationActivity() {
        val intent = Intent(this, LocationActivity::class.java).apply{
            putExtra("fullName", binding.userInputFullName.text.toString())
        }
        startActivity(intent)
    }
}