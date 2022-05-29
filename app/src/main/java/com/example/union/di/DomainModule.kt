package com.example.union.di

import com.example.domain.auth.AuthenticationUseCase
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.cloud.OrderInsertCloud
import com.example.domain.cloud.ProductInsertCloud
import com.example.domain.cloud.UserInsertCloud
import dagger.Module

@Module
class DomainModule {

    fun provideAuthenticationUseCase() : AuthenticationUseCase {
        return AuthenticationUseCase()
    }

    fun provideAuthorizationUseCase() : AuthorizationUseCase {
        return AuthorizationUseCase()
    }

    fun provideOrderInsertCloud() : OrderInsertCloud {
        return OrderInsertCloud()
    }

    fun provideProductInsertCloud() : ProductInsertCloud {
        return ProductInsertCloud()
    }

    fun provideUserInsertCloud() : UserInsertCloud {
        return UserInsertCloud()
    }



}