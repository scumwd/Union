package com.example.union.di

import com.example.domain.auth.AuthenticationUseCase
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.auth.CheckCurrentUser
import com.example.domain.auth.SignOutUseCase
import com.example.domain.cloud.OrderInsertCloud
import com.example.domain.cloud.ProductInsertCloud
import com.example.domain.cloud.UploadProductImage
import com.example.domain.cloud.UserInsertCloud
import com.example.domain.getFromDb.*
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.UserRepository
import com.example.domain.save.GetOrderFromFireBase
import com.example.domain.save.GetProductFromFireBase
import com.example.domain.save.GetUserFromFireBase
import com.example.domain.update.UpdateProductInFireBase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideAuthenticationUseCase(userRepository: UserRepository) : AuthenticationUseCase {
        return AuthenticationUseCase(userRepository)
    }

    @Provides
    fun provideAuthorizationUseCase(userRepository: UserRepository) : AuthorizationUseCase {
        return AuthorizationUseCase(userRepository)
    }

    @Provides
    fun provideOrderInsertCloud(orderRepository: OrderRepository) : OrderInsertCloud {
        return OrderInsertCloud(orderRepository)
    }

    @Provides
    fun provideProductInsertCloud(productRepository: ProductRepository) : ProductInsertCloud {
        return ProductInsertCloud(productRepository)
    }

    @Provides
    fun provideUserInsertCloud(userRepository: UserRepository) : UserInsertCloud {
        return UserInsertCloud(userRepository)
    }

    @Provides
    fun provideUpdateProductInFireBase(productRepository: ProductRepository) : UpdateProductInFireBase {
        return UpdateProductInFireBase(productRepository)
    }

    @Provides
    fun provideUploadProductImage(productRepository: ProductRepository) : UploadProductImage {
        return UploadProductImage(productRepository)
    }

    @Provides
    fun provideGetOrderById(orderRepository: OrderRepository,userRepository: UserRepository) : GetOrderById {
        return GetOrderById(orderRepository,userRepository)
    }

    @Provides
    fun provideSignOutUseCase(userRepository: UserRepository) : SignOutUseCase {
        return SignOutUseCase(userRepository)
    }
    @Provides
    fun provideCheckCurrentUser(userRepository: UserRepository) : CheckCurrentUser {
        return CheckCurrentUser(userRepository)
    }

    @Provides
    fun provideGetOrderFromFireBase(orderRepository: OrderRepository) : GetOrderFromFireBase {
        return GetOrderFromFireBase(orderRepository)
    }

    @Provides
    fun provideGetProductFromFireBase(productRepository: ProductRepository) : GetProductFromFireBase {
        return GetProductFromFireBase(productRepository)
    }

    @Provides
    fun provideGetUserFromFireBase(userRepository: UserRepository) : GetUserFromFireBase {
        return GetUserFromFireBase(userRepository)
    }

    @Provides
    fun provideGetOrderDb(orderRepository: OrderRepository) : GetOrderDb {
        return GetOrderDb(orderRepository)
    }

    @Provides
    fun provideGetProductDb(productRepository: ProductRepository) : GetProductDb {
        return GetProductDb(productRepository)
    }

    @Provides
    fun provideGetUserDb(userRepository: UserRepository) : GetUserDb {
        return GetUserDb(userRepository)
    }

    @Provides
    fun provideCheckOrderExists(orderRepository: OrderRepository) : CheckOrderExists {
        return CheckOrderExists(orderRepository)
    }

    @Provides
    fun provideCheckProductExists(productRepository: ProductRepository) : CheckProductExists {
        return CheckProductExists(productRepository)
    }

}