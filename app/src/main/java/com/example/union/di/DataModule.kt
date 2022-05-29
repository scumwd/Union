package com.example.union.di

import android.content.Context
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.order.OrderDao
import com.example.data.storage.order.OrderDb
import com.example.data.storage.product.ProductDao
import com.example.data.storage.product.ProductDb
import com.example.data.storage.user.UserStorage
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.UserRepository
import dagger.Module

@Module
class DataModule {

    fun provideProductRepository(productDao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(productDao)
    }

    fun provideProductDb(context: Context): ProductDao {
        return ProductDb.getInstance(context).getProductDao()
    }

    fun providerOrderRepository(orderDao: OrderDao): OrderRepository {
        return OrderRepositoryImpl(orderDao, "")
    }

    fun providerOrderDb(context: Context): OrderDao {
        return OrderDb.getInstance(context).getOrderDao()
    }

    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage)
    }

    fun provideUserDb(context: Context): UserStorage {
        return UserStorageImpl.getInstance(context).getUserDao()
    }

}