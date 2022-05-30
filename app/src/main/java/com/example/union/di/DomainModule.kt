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
    fun provideAuthenticationUseCase() : AuthenticationUseCase {
        return AuthenticationUseCase()
    }

    @Provides
    fun provideAuthorizationUseCase() : AuthorizationUseCase {
        return AuthorizationUseCase()
    }

    @Provides
    fun provideOrderInsertCloud() : OrderInsertCloud {
        return OrderInsertCloud()
    }

    @Provides
    fun provideProductInsertCloud() : ProductInsertCloud {
        return ProductInsertCloud()
    }

    @Provides
    fun provideUserInsertCloud() : UserInsertCloud {
        return UserInsertCloud()
    }

    @Provides
    fun provideUpdateProductInFireBase() : UpdateProductInFireBase {
        return UpdateProductInFireBase()
    }

    @Provides
    fun provideUploadProductImage() : UploadProductImage {
        return UploadProductImage()
    }

    @Provides
    fun provideSignOutUseCase() : SignOutUseCase {
        return SignOutUseCase()
    }
    @Provides
    fun provideCheckCurrentUser() : CheckCurrentUser {
        return CheckCurrentUser()
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