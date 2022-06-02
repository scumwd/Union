package com.example.data.storage.product

import android.widget.ImageView
import com.example.data.models.ProductCloud
import com.example.data.models.ProductCloudData

interface ProductFirebase {

    suspend fun uploadImage(photo: ImageView) : String

    suspend fun update(productCloud: ProductCloud)

    suspend fun insert(productCloud: ProductCloud)

    suspend fun getProducts() : List<ProductCloudData?>

}