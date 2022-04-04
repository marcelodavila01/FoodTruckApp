package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: LocationActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
    }

    //Uncomment this function and change AndroidManifest and "LocationActivity" -> to the activity it should
    //  navigate to, to make Locate Me Button functional.

    //fun sendData2(view: View) {
    //    val intent = Intent(this, LocationActivity::class.java)
    //    startActivity(intent)
    //}
}