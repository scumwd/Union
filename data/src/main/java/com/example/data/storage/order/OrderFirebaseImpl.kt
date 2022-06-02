package com.example.data.storage.order

import android.util.Log
import com.example.data.ORDER_KEY
import com.example.data.models.OrderCloud
import com.example.data.models.OrderCloudData
import com.example.domain.models.OrderDomain
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*

class OrderFirebaseImpl: OrderFirebase {

    override suspend fun insertOrder(orderCloud: OrderCloud) {
        val orderId: String = UUID.randomUUID().toString()
        val database = Firebase.database
        val myRef = database.getReference(ORDER_KEY)
        myRef.child(orderId).setValue(orderCloud)
    }

    override suspend fun getOrders() : List<OrderCloudData?> {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(ORDER_KEY)
        val listOrders : List<OrderCloudData?> = suspendCancellableCoroutine {
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val ordersList: MutableList<OrderCloudData?> = ArrayList<OrderCloudData?>()
                    for (ds in dataSnapshot.children) {
                        val messages: OrderCloudData? = ds.getValue(OrderCloudData::class.java)
                        ordersList.add(messages)
                    }
                    it.resume(ordersList) {}
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("firebaseErrorOrder", error.message)
                }
            }
            messageRef.addListenerForSingleValueEvent(valueEventListener)
        }
        return listOrders
    }
}