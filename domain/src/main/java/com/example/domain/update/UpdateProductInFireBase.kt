package com.example.domain.update

import com.example.domain.models.ProductDomain
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateProductInFireBase(val productRepository: ProductRepository) {

    fun execute(productDomain: ProductDomain) {
        GlobalScope.launch (Dispatchers.IO){
            productRepository.updateTotalAmountProduct(productDomain)
        }
    }
}