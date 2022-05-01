package com.example.foodtruckapp.database

class InitialFoodTruckData {
    companion object {
        val data = listOf<FoodTruck>(
            FoodTruck(
                "Pinch",
                30.283068838451634,
                -97.74089692091488,
                "11:30am - 8:00pm",
                "2011 Whitis Ave, Austin, TX 78705",
                4.3,
                false,
                "pinch"
            ),
            FoodTruck(
                "Pepe's Tacos",
                30.2739933630959,
                -97.7508396343992,
                "11:00am - 8:00pm",
                "704 N Lamar Blvd, Austin, TX 78703",
                4.5,
                false,
                "pepe_s_tacos"
            ),
            FoodTruck(
                "Chef Hong",
                30.288789703098274,
                -97.74574635372068,
                "11:00am - 8:00pm",
                "907 W 24th St, Austin, TX 78705",
                4.8,
                false,
                "chef_hong"
            ),
            FoodTruck(
                "El Taco Ranchero",
                37.42263473382312,
                -122.09414468856993,
                "10:00am - 8:30pm",
                "2490 Charleston Rd, Mountain View, CA 94043",
                4.7,
                false,
                "el_taco_ranchero"
            ),
            FoodTruck(
                "Paper Platez",
                37.414965828988784,
                -122.09414468856993,
                "11:00am - 9:00pm",
                "2135 Old Middlefield Way, Mountain View, CA 94043",
                5.0,
                false,
                "paper_platez"
            ),
            FoodTruck(
                "The Cookout Foodtruck",
                37.41639741750566,
                -122.09950910653023,
                "5:00am - 9:00pm",
                "2534 Old Middlefield Way, Mountain View, CA 94043",
                4.8,
                false,
                "the_cookout_food_truck"
            ),
        )
    }
}