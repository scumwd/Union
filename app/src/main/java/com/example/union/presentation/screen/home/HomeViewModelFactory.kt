package com.example.union.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.getFromDb.GetProductDb
import com.example.domain.save.GetProductFromFireBase
import com.example.domain.save.GetUserFromFireBase

class HomeViewModelFactory(
    val getProductDb: GetProductDb,
    val getProductFromFireBase: GetProductFromFireBase,
    val getUser: GetUserFromFireBase
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getProductFromFireBase = getProductFromFireBase,
            getProductDb = getProductDb,
            getUser = getUser
        ) as T
    }
}