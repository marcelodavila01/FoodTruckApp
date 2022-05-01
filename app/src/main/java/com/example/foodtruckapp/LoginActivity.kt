package com.example.foodtruckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.CurrentLogin
import com.example.foodtruckapp.databinding.ActivityLoginBinding
import com.example.foodtruckapp.databinding.ActivitySignupBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = AppDatabase.getInstance(this)!!

        setupToolbar()
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            //user click back
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun checkLogInInfo(): Boolean {
        val emailInput = binding.logInInputEmail.text.toString()
        if (emailInput.isEmpty()) {
            showToast("Enter an email")
            return false;
        }
        if (binding.logInInputPassword.text.toString().isEmpty()) {
            showToast("Enter a password")
            return false;
        }
        if (database.customerDao().getByEmail(emailInput) == null) {
            showToast("Email not found")
        }

        return true;
    }

    fun showToast(message: String) {
        val myToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        myToast.show()
    }

    private fun openLocationActivity() {
        val intent = Intent(this, LocationActivity::class.java)
        startActivity(intent)
    }

    fun logInCustomer() {
        if (!checkLogInInfo()) {
            return;
        }

        val email = binding.logInInputEmail.text.toString()
        val password = binding.logInInputPassword.text.toString()

        val loggedInCustomer = database.customerDao().getByEmailAndPassword(email, password)
        if (loggedInCustomer == null) {
            showToast("Invalid password")
            return
        }

        CurrentLogin.loginCustomer(loggedInCustomer)
        showToast("Welcome back ${loggedInCustomer.name}")

        openLocationActivity()
    }

    fun sendData(view: View) {
        logInCustomer()
    }
}