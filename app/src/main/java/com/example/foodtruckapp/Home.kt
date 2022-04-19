package com.example.foodtruckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun selectCustomer(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    fun selectOwner(view: View) {
        val intent = Intent(this, OwnerSignupActivity::class.java)
        startActivity(intent)
    }
}