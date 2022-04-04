package com.example.foodtruckapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.foodtruckapp.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun sendData(view: View) {
        val intent = Intent(this, LocationActivity::class.java).apply{
            putExtra("fullName", binding.userInputFullName.text.toString())
        }
        startActivity(intent)
    }
}