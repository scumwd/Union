package com.example.domain.models

import java.io.Serializable

class OrderDomain (
    var productID: Int = 0,
    val userId: String,
    var totalAmount: Int = 0
) : Serializable
