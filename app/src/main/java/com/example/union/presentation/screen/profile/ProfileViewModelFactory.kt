package com.example.union.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.auth.SignOutUseCase
import com.example.domain.getFromDb.GetOrderDb
import com.example.domain.getFromDb.GetProductDb
import com.example.domain.getFromDb.GetUserDb
import com.example.domain.save.GetOrderFromFireBase

class ProfileViewModelFactory(
    val getUserDb: GetUserDb,
    val getProductDb: GetProductDb,
    val getOrderDb: GetOrderDb,
    val getOrderFromFireBase: GetOrderFromFireBase,
    val signOutUseCase: SignOutUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            getUserDb = getUserDb,
            getProductDb = getProductDb,
            getOrderDb = getOrderDb,
            getOrderFromFireBase = getOrderFromFireBase,
            signOutUseCase = signOutUseCase
        ) as T
    }
}