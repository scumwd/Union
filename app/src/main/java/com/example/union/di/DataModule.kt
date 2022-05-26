package com.example.union.di

import android.content.Context
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.storage.order.OrderDao
import com.example.data.storage.product.ProductDao
import com.example.data.storage.product.ProductDb
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.ProductRepository
import dagger.Module
import dagger.hilt.android.qualifiers.ApplicationContext

/*@Module
class DataModule {

    fun provideProductRepository(productDao: ProductDao): ProductRepository{
        return ProductRepositoryImpl(productDao)
    }

    fun provideProductDb(@ApplicationContext context: Context): ProductDao{
        return ProductDb.getInstance(context).getProductDao()
    }

    fun providerOrderRepository(orderDao: OrderDao): OrderRepository{
        return OrderRepositoryImpl(orderDao,"")
    }
}*/