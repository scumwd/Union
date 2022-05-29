package com.example.domain.update

import com.example.domain.models.ProductDomain
import com.google.firebase.database.FirebaseDatabase

class UpdateProductInFireBase {

    private val PRODUCT_KEY: String = "Products"

    fun execute(productDomain: ProductDomain) {
        val rootRef = FirebaseDatabase.getInstance().reference
        val messageRef = rootRef.child(PRODUCT_KEY)
        messageRef.child(productDomain.productID).child("totalAmount")
            .setValue(productDomain.totalAmount)
    }
}