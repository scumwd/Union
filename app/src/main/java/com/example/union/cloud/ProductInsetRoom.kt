package com.example.union.cloud

import com.example.data.models.ProductData
import com.example.union.presentation.PRODUCT_REPOSITORY

class ProductInsetRoom {

    suspend fun insert(listProduct: List<ProductData>, onSuccess: () -> Unit ){
        listProduct.forEach {
            PRODUCT_REPOSITORY.insertProduct(it) {
                onSuccess
            }
        }
    }
}