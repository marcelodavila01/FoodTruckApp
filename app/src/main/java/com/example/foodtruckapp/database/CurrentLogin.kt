package com.example.foodtruckapp.database

object CurrentLogin {
    var currentCustomer: Customer? = null

    fun loginCustomer(customer: Customer) {
        currentCustomer = customer
    }

    fun logoutCustomer() {
        currentCustomer = null
    }
}