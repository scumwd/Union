package com.example.domain.cloud

import com.example.domain.models.ProductDomain
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductInsertCloud(val productRepository: ProductRepository) {

    fun insert(productDomain: ProductDomain) {
        GlobalScope.launch(Dispatchers.IO){
            productRepository.insertProductFireBase(productDomain)
        }
    }
}