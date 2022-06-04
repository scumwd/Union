package com.example.domain.save

import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetProductFromFireBase(private val productRepository: ProductRepository) {

    suspend fun getAllProduct(): Boolean {
        val productCloud = productRepository.getProductsFireBase()
        productRepository.insertProduct(productCloud) {}
        productRepository.updateProduct(productCloud) {}
        return true
    }
}