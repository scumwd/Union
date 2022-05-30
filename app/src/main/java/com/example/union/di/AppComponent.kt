package com.example.union.di

import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.ItemDetail.ItemDetailFragment
import com.example.union.presentation.screen.addItem.AddItemFragment
import com.example.union.presentation.screen.home.HomeFragment
import com.example.union.presentation.screen.login.AuthenticationActivity
import com.example.union.presentation.screen.profile.ProfileFragment
import com.example.union.presentation.screen.register.AuthorizationActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(addItemFragment: AddItemFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(authorizationActivity: AuthorizationActivity)

    fun inject(authenticationActivity: AuthenticationActivity)

    fun inject(itemDetailFragment: ItemDetailFragment)

    fun inject(homeFragment: HomeFragment)

}