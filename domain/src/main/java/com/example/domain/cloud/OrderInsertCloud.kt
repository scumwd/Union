package com.example.domain.cloud

import com.example.domain.models.OrderDomain
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class OrderInsertCloud {

    private val ORDER_KEY: String = "Orders"

    fun insert(orderDomain: OrderDomain) {
        val orderId: String = UUID.randomUUID().toString()
        val database = Firebase.database
        val myRef = database.getReference(ORDER_KEY)
        myRef.child(orderId).setValue(orderDomain)
    }

}