package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.Customer
import com.example.foodtruckapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
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

    fun checkCustomerInfo(): Boolean {
        if (binding.userInputFullName.text.toString().isEmpty()) {
            showToast("Enter a name")
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

        return true;
    }

    fun createCustomer() {
        if (!checkCustomerInfo()) {
            return;
        }

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

        openLocationActivity()
    }

    fun sendData(view: View) {
        createCustomer()
    }

    private fun openLocationActivity() {
        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
    }
}