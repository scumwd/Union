package com.example.union.presentation.screen.addItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Product
import com.example.union.presentation.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {
    fun insert(product: Product, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.insertProduct(product){
                onSuccess
            }
        }
}