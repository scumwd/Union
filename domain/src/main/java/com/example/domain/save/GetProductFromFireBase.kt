package com.example.domain.save

import android.util.Log
import com.example.domain.models.ProductCloudData
import com.example.domain.repository.ProductRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class GetProductFromFireBase {

    private val PRODUCT_KEY: String = "Products"

    fun getAllProduct(productRepository: ProductRepository) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(PRODUCT_KEY)
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val productsList: MutableList<ProductCloudData?> = ArrayList<ProductCloudData?>()
                for (ds in dataSnapshot.children) {
                    val messages: ProductCloudData? = ds.getValue(ProductCloudData::class.java)
                    productsList.add(messages)
                }
                productRepository.insertProduct(productsList) {}
                productRepository.updateProduct(productsList) {}
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebaseErrorProduct", error.message)
            }
        }
        messageRef.addListenerForSingleValueEvent(valueEventListener)
    }
}