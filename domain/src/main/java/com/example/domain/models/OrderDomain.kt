package com.example.domain.models

import java.io.Serializable

class OrderDomain (
    var productID: String = "",
    val userId: String,
    var totalAmount: Int = 0
) : Serializable
