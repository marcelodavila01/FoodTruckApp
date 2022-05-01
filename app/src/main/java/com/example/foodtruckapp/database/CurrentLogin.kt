package com.example.foodtruckapp.database

object CurrentLogin {
    private var currentCustomer: Customer? = null
    private var currentOwner: Owner? = null
    var isOwner = false

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