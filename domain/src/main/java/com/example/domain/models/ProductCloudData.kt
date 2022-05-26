package com.example.domain.models


class ProductCloudData {
    private var productID: String? = null
    private var productName: String? = null
    private var productLink: String = ""
    private var productPrice: Int = 0
    private var amount: Int = 0
    private var totalAmount: Int = 0
    private var city: String = ""
    private var productPhoto: String = ""

    fun ProductCloudData() {}


    fun ProductCloudData(
        productName: String,
        productID: String,
        productLink: String,
        productPrice: Int,
        amount: Int,
        totalAmount: Int,
        city: String,
        productPhoto: String
    ) {
        this.productName = productName
        this.productID = productID
        this.productLink = productLink
        this.productPrice = productPrice
        this.amount = amount
        this.totalAmount = totalAmount
        this.city = city
        this.productPhoto = productPhoto
    }

    fun getProductName(): String? {
        return productName; }

    fun getProductId(): String? {
        return productID; }

    fun getProductLink(): String {
        return productLink; }

    fun getProductPrice(): Int {
        return productPrice; }

    fun getAmount(): Int {
        return amount; }

    fun getTotalAmount(): Int {
        return totalAmount; }

    fun getCity(): String {
        return city; }

    fun getProductPhoto(): String {
        return productPhoto; }
}





