package com.example.union.di

import android.content.Context
import com.example.domain.auth.AuthenticationUseCase
import com.example.domain.auth.AuthorizationUseCase
import com.example.domain.auth.CheckCurrentUser
import com.example.domain.auth.SignOutUseCase
import com.example.domain.cloud.OrderInsertCloud
import com.example.domain.cloud.ProductInsertCloud
import com.example.domain.cloud.UploadProductImage
import com.example.domain.getFromDb.*
import com.example.domain.save.GetOrderFromFireBase
import com.example.domain.save.GetProductFromFireBase
import com.example.domain.save.GetUserFromFireBase
import com.example.domain.update.UpdateProductInFireBase
import com.example.union.presentation.screen.ItemDetail.ItemDetailViewModelFactory
import com.example.union.presentation.screen.addItem.AddItemFragment
import com.example.union.presentation.screen.addItem.AddItemViewModelFactory
import com.example.union.presentation.screen.home.HomeViewModelFactory
import com.example.union.presentation.screen.login.AuthenticationViewModelFactory
import com.example.union.presentation.screen.profile.ProfileViewModel
import com.example.union.presentation.screen.profile.ProfileViewModelFactory
import com.example.union.presentation.screen.register.AuthorizationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule (val context: Context){

    @Provides
    fun provideContext() : Context{
        return context
    }

    @Provides
    fun provideAddItemFactory(
        productInsertCloud: ProductInsertCloud,
        getProductFromFireBase: GetProductFromFireBase,
        uploadProductImage: UploadProductImage,
        checkProductExists: CheckProductExists
    ): AddItemViewModelFactory {
        return AddItemViewModelFactory(
            getProductFromFireBase = getProductFromFireBase,
            productInsertCloud = productInsertCloud,
            uploadProductImage = uploadProductImage,
            checkProductExists = checkProductExists
        )
    }

    @Provides
    fun provideProfileFactory(
        getUserDb: GetUserDb,
        getProductDb: GetProductDb,
        getOrderDb: GetOrderDb,
        getOrderFromFireBase: GetOrderFromFireBase,
        signOutUseCase: SignOutUseCase
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(
            getUserDb = getUserDb,
            getProductDb = getProductDb,
            getOrderDb = getOrderDb,
            getOrderFromFireBase = getOrderFromFireBase,
            signOutUseCase = signOutUseCase
        )
    }

    @Provides
    fun provideAuthorizationFactory(
        authorizationUseCase: AuthorizationUseCase
    ): AuthorizationViewModelFactory {
        return AuthorizationViewModelFactory(
            authorizationUseCase = authorizationUseCase
        )
    }

    @Provides
    fun provideAuthenticationFactory(
        authenticationUseCase: AuthenticationUseCase,
        getUser: GetUserFromFireBase,
        checkCurrentUser: CheckCurrentUser
    ): AuthenticationViewModelFactory {
        return AuthenticationViewModelFactory(
            authenticationUseCase = authenticationUseCase,
            getUser = getUser,
            checkCurrentUser = checkCurrentUser
        )
    }

    @Provides
    fun provideItemDetailFactory(
        getOrderFromFireBase: GetOrderFromFireBase,
        orderInsertCloud: OrderInsertCloud,
        updateProductInFireBase: UpdateProductInFireBase,
        checkOrderExists: CheckOrderExists
    ): ItemDetailViewModelFactory {
        return ItemDetailViewModelFactory(
            getOrderFromFireBase = getOrderFromFireBase,
            orderInsertCloud = orderInsertCloud,
            updateProductInFireBase = updateProductInFireBase,
            checkOrderExists = checkOrderExists
        )
    }

    @Provides
    fun provideHomeViewModelFactory(
        getProductFromFireBase: GetProductFromFireBase,
        getProductDb: GetProductDb
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
           getProductDb = getProductDb,
            getProductFromFireBase = getProductFromFireBase
        )
    }

}