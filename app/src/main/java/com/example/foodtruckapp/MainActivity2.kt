package com.example.foodtruckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}