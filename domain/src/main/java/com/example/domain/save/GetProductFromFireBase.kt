package com.example.domain.save

import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetProductFromFireBase(private val productRepository: ProductRepository) {

    fun getAllProduct() {
        GlobalScope.launch(Dispatchers.IO){
            val productCloud = productRepository.getProductsFireBase()
            productRepository.insertProduct(productCloud) {}
            productRepository.updateProduct(productCloud) {}
        }
    }
}