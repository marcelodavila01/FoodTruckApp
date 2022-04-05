package com.example.foodtruckapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruckapp.FifthData.FifthData
import com.example.foodtruckapp.FifthItemAdapter.ItemAdapter

class MainActivityRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = FifthData().loadAffirmations()

        val recyclerView = findViewById< RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this,myDataset)

        recyclerView.setHasFixedSize(true)
    }
}