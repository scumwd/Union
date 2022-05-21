package com.example.union.presentation.screen.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.storage.order.OrderDb
import com.example.data.storage.product.ProductDb
import com.example.domain.models.ProductDomain
import com.example.union.presentation.ORDER_REPOSITORY
import com.example.union.presentation.PRODUCT_REPOSITORY

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(userId: String) {
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        val daoOrder = OrderDb.getInstance(context).getOrderDao()
        ORDER_REPOSITORY = OrderRepositoryImpl(daoOrder, userId)
        PRODUCT_REPOSITORY = ProductRepositoryImpl(daoProduct)
    }

    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return PRODUCT_REPOSITORY.allProductDomain
    }

}