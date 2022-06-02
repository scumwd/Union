package com.example.union.presentation.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.getFromDb.GetProductDb
import com.example.domain.models.ProductDomain
import com.example.domain.save.GetProductFromFireBase
import com.example.domain.save.GetUserFromFireBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getProductFromFireBase: GetProductFromFireBase,
    private val getProductDb: GetProductDb,
    private val getUser: GetUserFromFireBase
): ViewModel() {


    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return getProductDb.execute()
    }

    fun getProductsFromFireBase() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductFromFireBase.getAllProduct()
        }
    }

    fun getUser(){
        getUser.getUser()
    }

}