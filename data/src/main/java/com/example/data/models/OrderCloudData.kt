package com.example.data.models

class OrderCloudData {
    private var productID: String = ""
    private var userId: String = ""
    private var totalAmount: Int = 0

    fun OrderCloudData() {}

    fun OrderCloudData(productId: String, userId: String, totalAmount: Int) {
        this.productID = productId
        this.userId = userId
        this.totalAmount = totalAmount
    }

    fun getProductId(): String {
        return productID
    }

    fun getUserId(): String {
        return userId
    }

    fun getTotalAmount(): Int {
        return totalAmount
    }
}