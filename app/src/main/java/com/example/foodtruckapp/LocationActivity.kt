package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.FifthModel.FifthFragment

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: LocationActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
    }

    fun openMap(view: View) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    fun openList(view: View)
    {
        val intent = Intent(this, FifthFragment::class.java)
        startActivity(intent)
    }
}