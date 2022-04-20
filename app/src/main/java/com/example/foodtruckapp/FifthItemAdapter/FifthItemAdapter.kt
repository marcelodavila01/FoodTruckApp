package com.example.foodtruckapp.FifthItemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtruckapp.R
import com.example.foodtruckapp.FifthModel.FifthFragment


//Adapter for the Recycler View in Main Activity and displays Affirmation data.
class ItemAdapter(private val context: Context, private val dataset: List<FifthFragment>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    // reference to view each affirmation item
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_fifth_image_list, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    // replace contents of a view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    // return size of dataset
    override fun getItemCount() = dataset.size
}