package com.example.union.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.getFromDb.GetProductDb
import com.example.domain.save.GetProductFromFireBase

class HomeViewModelFactory(
    val getProductDb: GetProductDb,
    val getProductFromFireBase: GetProductFromFireBase
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getProductFromFireBase = getProductFromFireBase,
            getProductDb = getProductDb
        ) as T
    }
}