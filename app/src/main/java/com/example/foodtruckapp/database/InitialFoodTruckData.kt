package com.example.foodtruckapp.database

class InitialFoodTruckData {
    companion object {
        val data = listOf<FoodTruck>(
            FoodTruck(
                1,
                "Pinch",
                30.283068838451634,
                -97.74089692091488,
                "11:30am - 8:00pm",
                "2011 Whitis Ave, Austin, TX 78705",
                4.3,
            ),
            FoodTruck(
                2,
                "Pepe's Tacos",
                30.2739933630959,
                -97.7508396343992,
                "11:00am - 8:00pm",
                "704 N Lamar Blvd, Austin, TX 78703",
                4.5,
            ),
            FoodTruck(
                3,
                "Chef Hong",
                30.288789703098274,
                -97.74574635372068,
                "11:00am - 8:00pm",
                "907 W 24th St, Austin, TX 78705",
                4.8,
            ),
            FoodTruck(
                4,
                "El Taco Ranchero",
                37.42263473382312,
                -122.09414468856993,
                "10:00am - 8:30pm",
                "2490 Charleston Rd, Mountain View, CA 94043",
                4.7,
            ),
            FoodTruck(
                5,
                "Paper Platez",
                37.414965828988784,
                -122.09414468856993,
                "11:00am - 9:00pm",
                "2135 Old Middlefield Way, Mountain View, CA 94043",
                5.0,
            ),
            FoodTruck(
                6,
                "The Cookout Foodtruck",
                37.41639741750566,
                -122.09950910653023,
                "5:00am - 9:00pm",
                "2534 Old Middlefield Way, Mountain View, CA 94043",
                4.8,
            ),
        )
    }
}