package com.example.union.presentation.screen.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.storage.order.OrderDb
import com.example.data.storage.product.ProductDb
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.presentation.ORDER_REPOSITORY
import com.example.union.presentation.PRODUCT_REPOSITORY

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(userId: String) {
        val orderDao = OrderDb.getInstance(context).getOrderDao()
        val daoProduct = ProductDb.getInstance(context).getProductDao()
        PRODUCT_REPOSITORY = ProductRepositoryImpl(daoProduct)
        ORDER_REPOSITORY = OrderRepositoryImpl(orderDao, userId)
    }

    fun getAllOrder(): LiveData<List<OrderDomain>> {
        return ORDER_REPOSITORY.allOrderDomain
    }

    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return PRODUCT_REPOSITORY.allProductDomain
    }
}