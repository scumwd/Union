package com.example.domain.getFromDb

import com.example.domain.models.ProductDomain
import com.example.domain.repository.ProductRepository

class CheckProductExists(val productRepository: ProductRepository) {

    fun execute(productLink: String): ProductDomain?{
        return productRepository.getListProduct(productLink)
    }
}