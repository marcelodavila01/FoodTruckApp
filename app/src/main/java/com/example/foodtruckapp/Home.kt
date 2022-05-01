package com.example.foodtruckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.foodtruckapp.database.CurrentLogin

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (!CurrentLogin.seenAnimation) {
            CurrentLogin.seenAnimation = true
            applyAnimations()
        }
    }

    fun applyAnimations() {
        val imageView = findViewById<ImageView>(R.id.imageView4);
        val imageAnim = AnimationUtils.loadAnimation(applicationContext,
            R.anim.slide_in_right);
        imageView.startAnimation(imageAnim);

        val customerButton = findViewById<Button>(R.id.button)
        val customerButtonAnim = AnimationUtils.loadAnimation(applicationContext,
            R.anim.scale_in);
        customerButton.startAnimation(customerButtonAnim);

        val ownerButton = findViewById<Button>(R.id.button4)
        val ownerButtonAnim = AnimationUtils.loadAnimation(applicationContext,
            R.anim.scale_in);
        ownerButton.startAnimation(ownerButtonAnim);

        val textView = findViewById<TextView>(R.id.textView)
        val textAnim = AnimationUtils.loadAnimation(applicationContext,
            R.anim.fade_in);
        textView.startAnimation(textAnim);
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