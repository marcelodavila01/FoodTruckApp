package com.example.foodtruckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class FoodTruckDetailActivity : AppCompatActivity() {
    private var backToMap = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_truck_detail)

        setupToolbar()

        setDetails(
            intent.getStringExtra("name", )!!,
            intent.getStringExtra("hours")!!,
            intent.getStringExtra("address")!!,
            intent.getDoubleExtra("rating", 0.0),
        )

        backToMap = intent.getBooleanExtra("fromMap", false)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }


    fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setDetails(name: String, hours: String, address: String, rating: Double) {
        findViewById<TextView>(R.id.name).setText(name)
        findViewById<TextView>(R.id.hours).setText("Hours: $hours")
        findViewById<TextView>(R.id.address).setText("Address: \n$address")
        findViewById<TextView>(R.id.rating).setText("${rating.toString()} / 5.0")
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            //user click back
            var intent = Intent(this, ListActivity::class.java)
            if (backToMap) {
                intent = Intent(this, MapsActivity::class.java)
            }

            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}