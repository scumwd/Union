package com.example.domain.getFromDb

import androidx.lifecycle.LiveData
import com.example.domain.models.OrderDomain
import com.example.domain.repository.OrderRepository
import com.google.firebase.auth.FirebaseAuth

class GetOrderDb(val orderRepository: OrderRepository) {

    lateinit var mAuth: FirebaseAuth

    fun execute(): LiveData<List<OrderDomain>> {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        return orderRepository.allOrderDomain(userId)
    }
}