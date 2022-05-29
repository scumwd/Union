package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.ProductData
import com.example.data.storage.product.ProductDao
import com.example.domain.models.ProductCloudData
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

    override fun getListProduct(productLink: String): ProductDomain? {
        val productDb: ProductData? = productDao.getProduct(productLink = productLink)
        val productDomain: ProductDomain?
        if (productDb?.productCity == null) {
            productDomain = null
        } else {
            productDomain = ProductDomain(
                productID = productDb.productId,
                productLink = productDb.productLink,
                productPhoto = productDb.productPhoto,
                productPrice = productDb.productPrice,
                productName = productDb.productName,
                amount = productDb.amount,
                totalAmount = productDb.totalAmount,
                city = productDb.productCity
            )
        }

        return productDomain
    }

    override fun insertProduct(listCloud: MutableList<ProductCloudData?>, onSuccess: () -> Unit) {
        var listRoom: List<ProductData> = mutableListOf()
        listCloud.forEach { cloud ->
            val productData = ProductData(
                productId = cloud?.getProductId().toString(),
                productName = cloud?.getProductName().toString(),
                productLink = cloud?.getProductLink().toString(),
                productPrice = cloud?.getProductPrice().toString().toInt(),
                productCity = cloud?.getCity().toString(),
                productPhoto = cloud?.getProductPhoto().toString(),
                amount = cloud?.getAmount().toString().toInt(),
                totalAmount = cloud?.getTotalAmount().toString().toInt()
            )
            listRoom = listRoom + productData
        }
        listRoom.forEach {
            productDao.insert(productData = it)
        }

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

    override fun updateProduct(listCloud: MutableList<ProductCloudData?>, onSuccess: () -> Unit) {
        var listRoom: List<ProductData> = mutableListOf()
        listCloud.forEach { cloud ->
            val productData = ProductData(
                productId = cloud?.getProductId().toString(),
                productName = cloud?.getProductName().toString(),
                productLink = cloud?.getProductLink().toString(),
                productPrice = cloud?.getProductPrice().toString().toInt(),
                productCity = cloud?.getCity().toString(),
                productPhoto = cloud?.getProductPhoto().toString(),
                amount = cloud?.getAmount().toString().toInt(),
                totalAmount = cloud?.getTotalAmount().toString().toInt()
            )
            listRoom = listRoom + productData
        }
        listRoom.forEach {
            productDao.update(productData = it)
        }
        onSuccess()
    }
}