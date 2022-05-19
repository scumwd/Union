package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.data.models.Product
import com.example.data.storage.product.ProductDao
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {

    override val allProduct: LiveData<List<com.example.domain.models.Product>>
        get() = productDao.getAllProduct().map { list -> list.map{ productLocal ->
                com.example.domain.models.Product(
                    productID = productLocal.productId,
                    productPhoto = productLocal.productPhoto,
                    productPrice = productLocal.productPrice,
                    productLink = productLocal.productLink,
                    productName = productLocal.productName,
                    city = productLocal.productCity,
                    amount = productLocal.amount,
                    totalAmount = productLocal.totalAmount
                )
            }
        }

    override suspend fun insertProduct(product: com.example.domain.models.Product, onSuccess: () -> Unit) {
        val productDb = Product(
            productName = product.productName,
            productCity = product.city,
            productLink = product.productLink,
            productPrice = product.productPrice,
            amount = product.amount,
            productPhoto = product.productPhoto
        )
        productDao.insert(product = productDb)
        onSuccess()
    }

    override suspend fun deleteProduct(product: com.example.domain.models.Product, onSuccess: () -> Unit) {
        val productDb = Product(
            productName = product.productName,
            productCity = product.city,
            productLink = product.productLink,
            productPrice = product.productPrice,
            amount = product.amount,
            productPhoto = product.productPhoto
        )
        productDao.delete(product = productDb)
        onSuccess()
    }
}