package com.example.domain.getFromDb

import androidx.lifecycle.LiveData
import com.example.domain.models.ProductDomain
import com.example.domain.repository.ProductRepository

class GetProductDb(val productRepository: ProductRepository) {

    fun execute(): LiveData<List<ProductDomain>> {
        return productRepository.allProductDomain
    }
}