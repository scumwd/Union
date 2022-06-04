package com.example.union.di

import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.ItemDetail.ItemDetailFragment
import com.example.union.presentation.screen.ItemDetail.ItemDetailViewModel
import com.example.union.presentation.screen.login.AuthenticationActivity
import com.example.union.presentation.screen.orderDetail.OrderDetailFragment
import com.example.union.presentation.screen.register.AuthorizationActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, ViewModelModule:: class])
@Singleton
interface AppComponent {

    @Singleton
    fun inject(mainActivity: MainActivity)

    @Singleton
    fun inject(itemDetailFragment: ItemDetailFragment)

    @Singleton
    fun inject(orderDetailFragment: OrderDetailFragment)

    @Singleton
    fun inject(authorizationActivity: AuthorizationActivity)

    @Singleton
    fun inject(authenticationActivity: AuthenticationActivity)

}