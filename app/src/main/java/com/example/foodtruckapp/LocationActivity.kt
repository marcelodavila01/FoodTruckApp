package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.FifthModel.FifthFragment
import com.example.foodtruckapp.database.AppDatabase

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: LocationActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        setupToolbar()

        val currentCustomer = AppDatabase.getInstance(this)?.customerDao()?.getLast()
        findViewById<TextView>(R.id.displayName_text).text = currentCustomer?.name
    }

    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            //user click back
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
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
}