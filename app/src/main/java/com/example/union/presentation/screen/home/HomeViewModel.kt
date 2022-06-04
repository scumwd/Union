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
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getProductFromFireBase: GetProductFromFireBase,
    private val getUser: GetUserFromFireBase,
    private val getProductDb: GetProductDb
) : ViewModel() {


    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return getProductDb.execute()
    }

    suspend fun getProductsFromFireBase() : Boolean {
        return suspendCancellableCoroutine {
            viewModelScope.launch(Dispatchers.IO) {
                it.resume(getProductFromFireBase.getAllProduct()) {}
            }
        }

    }

    suspend fun getUserFirebase() : Boolean = suspendCancellableCoroutine {continuation ->
        viewModelScope.launch {
            continuation.resume(getUser.getUser()) {}
        }
    }

}