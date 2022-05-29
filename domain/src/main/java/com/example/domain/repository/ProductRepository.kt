package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.ProductCloudData
import com.example.domain.models.ProductDomain

interface ProductRepository {

    val allProductDomain: LiveData<List<ProductDomain>>

    fun getListProduct(productLink: String): ProductDomain?

    fun insertProduct(listCloud: MutableList<ProductCloudData?>, onSuccess: () -> Unit)

    suspend fun deleteProduct(productDomain: ProductDomain, onSuccess: () -> Unit)

    fun updateProduct(listCloud: MutableList<ProductCloudData?>, onSuccess: () -> Unit)

}