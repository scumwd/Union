package com.example.data.models


class ProductCloudData {
    private var productID: String? = null
    private var productName: String? = null
    private var productLink: String = ""
    private var productPrice: Double = 0.0
    private var amount: Int = 0
    private var totalAmount: Int = 0
    private var productCity: String = ""
    private var productPhoto: String = ""

    fun ProductCloudData() {}


    fun ProductCloudData(
        productName: String,
        productID: String,
        productLink: String,
        productPrice: Double,
        amount: Int,
        totalAmount: Int,
        productCity: String,
        productPhoto: String
    ) {
        this.productName = productName
        this.productID = productID
        this.productLink = productLink
        this.productPrice = productPrice
        this.amount = amount
        this.totalAmount = totalAmount
        this.productCity = productCity
        this.productPhoto = productPhoto
    }

    fun getProductName(): String? {
        return productName; }

    fun getProductId(): String? {
        return productID; }

    fun getProductLink(): String {
        return productLink; }

    fun getProductPrice(): Double {
        return productPrice; }

    fun getAmount(): Int {
        return amount; }

    fun getTotalAmount(): Int {
        return totalAmount; }

    fun getProductCity(): String {
        return productCity; }

    fun getProductPhoto(): String {
        return productPhoto; }
}





