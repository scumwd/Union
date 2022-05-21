package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.ProductData
import com.example.data.storage.product.ProductDao
import com.example.domain.models.ProductDomain
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {

    override val allProductDomain: LiveData<List<ProductDomain>>
        get() = productDao.getAllProduct().map { list ->
            list.map { productLocal ->
                ProductDomain(
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

    override suspend fun insertProduct(productDomain: ProductDomain, onSuccess: () -> Unit) {
        val productDb = ProductData(
            productName = productDomain.productName,
            productCity = productDomain.city,
            productLink = productDomain.productLink,
            productPrice = productDomain.productPrice,
            amount = productDomain.amount,
            productPhoto = productDomain.productPhoto
        )
        productDao.insert(productData = productDb)
        onSuccess()
    }

    override suspend fun deleteProduct(productDomain: ProductDomain, onSuccess: () -> Unit) {
        val productDb = ProductData(
            productName = productDomain.productName,
            productCity = productDomain.city,
            productLink = productDomain.productLink,
            productPrice = productDomain.productPrice,
            amount = productDomain.amount,
            productPhoto = productDomain.productPhoto,
            totalAmount = productDomain.totalAmount,
            productId = productDomain.productID
        )
        productDao.delete(productData = productDb)
        onSuccess()
    }

    override suspend fun updateProduct(productDomain: ProductDomain, onSuccess: () -> Unit) {
        val productDb = ProductData(
            productName = productDomain.productName,
            productCity = productDomain.city,
            productLink = productDomain.productLink,
            productPrice = productDomain.productPrice,
            amount = productDomain.amount,
            totalAmount = productDomain.totalAmount,
            productPhoto = productDomain.productPhoto,
            productId = productDomain.productID

        )
        productDao.update(productDb)
        onSuccess()
    }
}