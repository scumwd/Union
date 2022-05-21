package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.ProductDomain

interface ProductRepository {
    val allProductDomain: LiveData<List<ProductDomain>>
    suspend fun insertProduct(productDomain: ProductDomain, onSuccess: () -> Unit)
    suspend fun deleteProduct(productDomain: ProductDomain, onSuccess: () -> Unit)
    suspend fun updateProduct(productDomain: ProductDomain, onSuccess: () -> Unit)
}