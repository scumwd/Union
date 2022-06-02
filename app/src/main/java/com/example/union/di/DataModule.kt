package com.example.union.di

import android.content.Context
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.ProductRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.data.storage.order.OrderDao
import com.example.data.storage.order.OrderDb
import com.example.data.storage.order.OrderFirebase
import com.example.data.storage.order.OrderFirebaseImpl
import com.example.data.storage.product.ProductDao
import com.example.data.storage.product.ProductDb
import com.example.data.storage.product.ProductFirebase
import com.example.data.storage.product.ProductFirebaseImpl
import com.example.data.storage.user.UserFirebase
import com.example.data.storage.user.UserFirebaseImpl
import com.example.data.storage.user.UserStorage
import com.example.data.storage.user.UserStorageImpl
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideProductRepository(productDao: ProductDao, productFirebase: ProductFirebase): ProductRepository {
        return ProductRepositoryImpl(productDao, productFirebase)
    }

    @Provides
    fun provideProductFirebase(): ProductFirebase {
        return ProductFirebaseImpl()
    }

    @Provides
    fun provideProductDb(context: Context): ProductDao {
        return ProductDb.getInstance(context).getProductDao()
    }

    @Provides
    fun providerOrderRepository(orderDao: OrderDao, orderFirebase: OrderFirebase): OrderRepository {
        return OrderRepositoryImpl(orderDao,orderFirebase)
    }

    @Provides
    fun provideOrderFirebase(): OrderFirebase {
        return OrderFirebaseImpl()
    }

    @Provides
    fun providerOrderDb(context: Context): OrderDao {
        return OrderDb.getInstance(context).getOrderDao()
    }

    @Provides
    fun provideUserRepository(userStorage: UserStorage,userFirebase: UserFirebase): UserRepository {
        return UserRepositoryImpl(userStorage,userFirebase)
    }

    @Provides
    fun provideUserFirebase(): UserFirebase {
        return UserFirebaseImpl()
    }

    @Provides
    fun provideUserDb(context: Context): UserStorage {
        return UserStorageImpl.getInstance(context).getUserDao()
    }

}