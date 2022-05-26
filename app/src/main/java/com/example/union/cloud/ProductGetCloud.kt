package com.example.union.cloud

import com.example.domain.models.ProductCloudData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class ProductGetCloud {

    private val PRODUCT_KEY: String = "Products"

    fun getAllProduct() {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(PRODUCT_KEY)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val productsList: MutableList<ProductCloudData?> = ArrayList<ProductCloudData?>()
                for (ds in dataSnapshot.children) {
                    val messages: ProductCloudData? = ds.getValue(ProductCloudData::class.java)
                    productsList.add(messages)
                }
                val productMap = ProductMap()
                productMap.mapProduct(productsList)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        messageRef.addListenerForSingleValueEvent(valueEventListener)
    }
}