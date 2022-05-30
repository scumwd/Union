package com.example.union.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.getFromDb.GetProductDb
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetProductFromFireBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductFromFireBase: GetProductFromFireBase,
    private val getProductDb: GetProductDb
): ViewModel() {


    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return getProductDb.execute()
    }

    fun getProductsFromFireBase() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductFromFireBase.getAllProduct()
        }
    }

}