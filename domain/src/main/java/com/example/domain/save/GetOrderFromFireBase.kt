package com.example.domain.save

import android.util.Log
import com.example.domain.models.OrderCloudData
import com.example.domain.repository.OrderRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class GetOrderFromFireBase {

    private val ORDER_KEY: String = "Orders"

    fun getAllProduct(orderRepository: OrderRepository) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(ORDER_KEY)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val productsList: MutableList<OrderCloudData?> = ArrayList<OrderCloudData?>()
                for (ds in dataSnapshot.children) {
                    val messages: OrderCloudData? = ds.getValue(OrderCloudData::class.java)
                    productsList.add(messages)
                }
                orderRepository.insertOrder(productsList) {}
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebaseErrorOrder", error.message)
            }
        }
        messageRef.addListenerForSingleValueEvent(valueEventListener)
    }
}