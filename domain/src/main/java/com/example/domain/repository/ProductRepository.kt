package com.example.domain.repository

import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.example.domain.models.ProductDomain

interface ProductRepository {

    val allProductDomain: LiveData<List<ProductDomain>>

    fun getListProduct(productLink: String): ProductDomain?

    fun insertProduct(listCloud: List<ProductDomain>, onSuccess: () -> Unit)

    suspend fun deleteProduct(productDomain: ProductDomain, onSuccess: () -> Unit)

    fun updateProduct(listCloud: List<ProductDomain>, onSuccess: () -> Unit)

    suspend fun insertProductFireBase(productDomain: ProductDomain)

    suspend fun updateTotalAmountProduct(productDomain: ProductDomain)

    suspend fun getProductsFireBase() : List<ProductDomain>

    suspend fun uploadProductImage(photo: ImageView) : String

}