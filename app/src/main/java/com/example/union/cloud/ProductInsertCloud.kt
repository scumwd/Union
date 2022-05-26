package com.example.union.cloud

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import com.google.firebase.database.DatabaseError
import android.util.Log
import com.example.domain.models.ProductCloudData
import com.example.domain.models.ProductDomain
import com.google.firebase.database.DataSnapshot
import java.util.ArrayList
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ProductInsertCloud {

    private val PRODUCT_KEY: String = "Products"

    fun insert(productDomain: ProductDomain) {
        val productUID = UUID.randomUUID()
        val database = Firebase.database
        val myRef = database.getReference(PRODUCT_KEY)
        productDomain.productID = productUID.toString()
        myRef.child(productUID.toString()).setValue(productDomain)
    }
}