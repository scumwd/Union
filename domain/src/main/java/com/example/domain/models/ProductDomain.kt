package com.example.domain.models

import java.io.Serializable

class ProductDomain(
    var productID: String = "",
    val productName: String,
    val productLink: String,
    val productPrice: Double,
    val amount: Int,
    var totalAmount: Int = 0,
    val city: String,
    val productPhoto: String
) : Serializable