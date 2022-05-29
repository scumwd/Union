package com.example.domain.cloud

import com.example.domain.models.ProductDomain
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

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