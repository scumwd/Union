package com.example.union.presentation.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.auth.SignOutUseCase
import com.example.domain.getFromDb.GetOrderDb
import com.example.domain.getFromDb.GetProductDb
import com.example.domain.getFromDb.GetUserDb
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.domain.models.UserWithUID
import com.example.domain.save.GetOrderFromFireBase
import com.example.domain.save.GetUserFromFireBase
import com.google.android.gms.tasks.SuccessContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserDb: GetUserDb,
    private val getProductDb: GetProductDb,
    private val getOrderDb: GetOrderDb,
    private val getOrderFromFireBase: GetOrderFromFireBase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    fun getAllOrder(): LiveData<List<OrderDomain>> {
        return getOrderDb.execute()
    }

    fun getAllProduct(): LiveData<List<ProductDomain>> {
        return getProductDb.execute()
    }

    fun getOrder() {
        viewModelScope.launch(Dispatchers.IO) { getOrderFromFireBase.getAllProduct() }
    }


    fun getUserDb(): LiveData<UserWithUID> {
        return getUserDb.execute()
    }

    fun signOut() {
        signOutUseCase.execute()
    }
}