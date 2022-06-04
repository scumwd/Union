package com.example.data.repository

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.ProductCloud
import com.example.data.models.ProductRoom
import com.example.data.storage.product.ProductDao
import com.example.data.storage.product.ProductFirebase
import com.example.domain.models.ProductDomain
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val productFirebase: ProductFirebase
) : ProductRepository {

    override val allProductDomain: LiveData<List<ProductDomain>>
        get() = productDao.getAllProduct().map { list ->
            list.map { productLocal ->
                ProductDomain(
                    productID = productLocal.productID,
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
        val productDb: ProductRoom? = productDao.getProduct(productLink = productLink)
        val productDomain: ProductDomain?
        if (productDb?.productCity == null) {
            productDomain = null
        } else {
            productDomain = ProductDomain(
                productID = productDb.productID,
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

    override fun insertProduct(listCloud: List<ProductDomain>, onSuccess: () -> Unit) {
        var listRoom: List<ProductRoom> = mutableListOf()
        listCloud.forEach { cloud ->
            val productData = ProductRoom(
                productID = cloud.productID,
                productName = cloud.productName,
                productLink = cloud.productLink,
                productPrice = cloud.productPrice,
                productCity = cloud.city,
                productPhoto = cloud.productPhoto,
                amount = cloud.amount,
                totalAmount = cloud.totalAmount
            )
            listRoom = listRoom + productData
        }
        listRoom.forEach {
            productDao.insert(productRoom = it)
        }

        onSuccess()
    }

    override suspend fun deleteProduct(productDomain: ProductDomain, onSuccess: () -> Unit) {
        val productDb = ProductRoom(
            productName = productDomain.productName,
            productCity = productDomain.city,
            productLink = productDomain.productLink,
            productPrice = productDomain.productPrice,
            amount = productDomain.amount,
            productPhoto = productDomain.productPhoto,
            totalAmount = productDomain.totalAmount,
            productID = productDomain.productID
        )
        productDao.delete(productRoom = productDb)
        onSuccess()
    }

    override fun updateProduct(listCloud: List<ProductDomain>, onSuccess: () -> Unit) {
        var listRoom: List<ProductRoom> = mutableListOf()
        listCloud.forEach { cloud ->
            val productData = ProductRoom(
                productID = cloud.productID,
                productName = cloud.productName,
                productLink = cloud.productLink,
                productPrice = cloud.productPrice,
                productCity = cloud.city,
                productPhoto = cloud.productPhoto,
                amount = cloud.amount,
                totalAmount = cloud.totalAmount
            )
            listRoom = listRoom + productData
        }
        listRoom.forEach {
            productDao.update(productRoom = it)
        }
        onSuccess()
    }

    override suspend fun insertProductFireBase(productDomain: ProductDomain) {
        productFirebase.insert(
            ProductCloud(
                productName = productDomain.productName,
                productCity = productDomain.city,
                productLink = productDomain.productLink,
                productPrice = productDomain.productPrice,
                amount = productDomain.amount,
                productPhoto = productDomain.productPhoto,
                totalAmount = productDomain.totalAmount,
                productID = productDomain.productID
            )
        )
    }

    override suspend fun updateTotalAmountProduct(productDomain: ProductDomain) {
        val productCloud = ProductCloud(
            productName = productDomain.productName,
            productCity = productDomain.city,
            productLink = productDomain.productLink,
            productPrice = productDomain.productPrice,
            amount = productDomain.amount,
            productPhoto = productDomain.productPhoto,
            totalAmount = productDomain.totalAmount,
            productID = productDomain.productID
        )
        productFirebase.update(productCloud)
    }

    override suspend fun getProductsFireBase(): List<ProductDomain> {
        val listFirebase = productFirebase.getProducts()
        val listDomain: List<ProductDomain> = listFirebase.map { cloud ->
            ProductDomain(
                productID = cloud?.getProductId().toString(),
                productName = cloud?.getProductName().toString(),
                productLink = cloud?.getProductLink().toString(),
                productPrice = cloud?.getProductPrice().toString().toDouble(),
                city = cloud?.getProductCity().toString(),
                productPhoto = cloud?.getProductPhoto().toString(),
                amount = cloud?.getAmount().toString().toInt(),
                totalAmount = cloud?.getTotalAmount().toString().toInt()
            )
        }
        return listDomain
    }

    override suspend fun uploadProductImage(photo: ImageView): String {
        return productFirebase.uploadImage(photo)
    }
}