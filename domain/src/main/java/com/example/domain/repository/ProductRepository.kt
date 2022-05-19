package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.Product

interface ProductRepository {
    val allProduct: LiveData<List<Product>>
    suspend fun insertProduct(product: Product, onSuccess:() -> Unit )
    suspend fun deleteProduct(product: Product, onSuccess:() -> Unit )
}