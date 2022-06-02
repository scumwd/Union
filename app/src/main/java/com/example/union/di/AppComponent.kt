package com.example.union.di

import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.ItemDetail.ItemDetailFragment
import com.example.union.presentation.screen.ItemDetail.ItemDetailViewModel
import com.example.union.presentation.screen.login.AuthenticationActivity
import com.example.union.presentation.screen.register.AuthorizationActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, ViewModelModule:: class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(itemDetailFragment: ItemDetailFragment)

    fun inject(authorizationActivity: AuthorizationActivity)

    fun inject(authenticationActivity: AuthenticationActivity)

}