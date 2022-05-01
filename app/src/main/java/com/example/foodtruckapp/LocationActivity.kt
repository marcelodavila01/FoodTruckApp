package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.database.AppDatabase
import com.example.foodtruckapp.database.CurrentLogin

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: LocationActivity
    private lateinit var database: AppDatabase
    private var isOwner: Boolean = CurrentLogin.isOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        setupToolbar()

        database = AppDatabase.getInstance(this)!!

        if (CurrentLogin.isOwner) {
            val currentOwner = CurrentLogin.getCurrentOwner()
            if (currentOwner == null) {
                showToast("Please log in")
                openHome()
            } else {
                findViewById<TextView>(R.id.displayName_text).text = currentOwner.name
            }

            findViewById<Button>(R.id.edit_button).visibility = View.VISIBLE
        } else {
            val currentCustomer = CurrentLogin.getCurrentCustomer()
            if (currentCustomer == null) {
                showToast("Please log in")
                openHome()
            } else {
                findViewById<TextView>(R.id.displayName_text).text = currentCustomer.name
            }

            findViewById<Button>(R.id.edit_button).visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_with_logout, menu);
        return true
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            //user click back
            openHome()
            true
        }

        R.id.action_log_out -> {
            if (isOwner) {
                CurrentLogin.logoutOwner()
            } else {
                CurrentLogin.logoutCustomer()
            }

            openHome()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun openMap(view: View) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun openList(view: View)
    {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    fun openHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    fun showToast(message: String) {
        val myToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        myToast.show()
    }

    fun openEdit(view: View) {
        val intent = Intent(this, FoodTruckEditActivity::class.java)
        startActivity(intent)
    }
}