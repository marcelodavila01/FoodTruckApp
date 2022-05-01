package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.CurrentLogin
import com.example.foodtruckapp.database.Customer
import com.example.foodtruckapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = AppDatabase.getInstance(this)!!

        setupToolbar()

        loginExistingCustomer()
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_login, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_log_in -> {
            //user click back
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun loginExistingCustomer() {
        val currentCustomer = CurrentLogin.currentCustomer

        if (currentCustomer != null) {
            showToast("Logged in ${currentCustomer.name}")

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

        if (database.customerDao().getByEmail(emailInput) != null) {
            showToast("Email already exists")
            return false
        }

        return true
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

        database.customerDao().insert(newCustomer)
        CurrentLogin.loginCustomer(newCustomer)

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