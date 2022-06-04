package com.example.union.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.union.presentation.ViewModelFactory
import com.example.union.presentation.screen.ItemDetail.ItemDetailViewModel
import com.example.union.presentation.screen.addItem.AddItemViewModel
import com.example.union.presentation.screen.home.HomeViewModel
import com.example.union.presentation.screen.login.AuthenticationViewModel
import com.example.union.presentation.screen.orderDetail.OrderDetailViewModel
import com.example.union.presentation.screen.profile.ProfileViewModel
import com.example.union.presentation.screen.register.AuthorizationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddItemViewModel::class)
    fun bindAddItemViewModel(
        viewModel: AddItemViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailViewModel::class)
    fun bindOrderDetailViewModel(
        viewModel: OrderDetailViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(
        viewModel: HomeViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    fun bindAuthenticationViewModel(
        viewModel: AuthenticationViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindAuthorizationViewModel(
        viewModel: AuthorizationViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailViewModel::class)
    fun bindItemDetailViewModel(
        viewModel: ItemDetailViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(
        viewModel: ProfileViewModel
    ): ViewModel
}