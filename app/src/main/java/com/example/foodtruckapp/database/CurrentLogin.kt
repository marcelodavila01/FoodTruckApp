package com.example.foodtruckapp.database

import android.location.Geocoder
import java.util.*

object CurrentLogin {
    private var currentCustomer: Customer? = null
    private var currentOwner: Owner? = null
    var isOwner = false
    var seenAnimation = false

    fun loginCustomer(customer: Customer) {
        currentCustomer = customer
        isOwner = false
    }

    fun logoutCustomer() {
        currentCustomer = null
    }

    fun getCurrentCustomer(): Customer? {
        return currentCustomer
    }

    fun loginOwner(owner: Owner) {
        currentOwner = owner
        isOwner = true
    }

    fun logoutOwner() {
        currentOwner = null
    }

    fun getCurrentOwner(): Owner? {
        return currentOwner
    }
}