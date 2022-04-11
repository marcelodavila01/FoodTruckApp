package com.example.foodtruckapp.FifthData

import com.example.foodtruckapp.R
import com.example.foodtruckapp.FifthModel.FifthFragment


class FifthData {
    fun loadAffirmations(): List<FifthFragment>{
        return listOf<FifthFragment>(
            FifthFragment(R.string.image1, R.drawable.no_image_template),
            FifthFragment(R.string.image2, R.drawable.no_image_template),
            FifthFragment(R.string.image3, R.drawable.no_image_template),
            FifthFragment(R.string.image4, R.drawable.no_image_template),
            FifthFragment(R.string.image5, R.drawable.no_image_template),
            FifthFragment(R.string.image6, R.drawable.no_image_template),
            FifthFragment(R.string.image7, R.drawable.no_image_template),
            FifthFragment(R.string.image8, R.drawable.no_image_template),
            FifthFragment(R.string.image9, R.drawable.no_image_template),
            FifthFragment(R.string.image10, R.drawable.no_image_template)
        )
    }

}