package com.example.domain.getFromDb

import com.example.domain.models.OrderDomain
import com.example.domain.repository.OrderRepository
import com.google.firebase.auth.FirebaseAuth

class CheckOrderExists(val orderRepository: OrderRepository) {

    lateinit var mAuth: FirebaseAuth

    suspend fun execute(productId: String): Boolean{
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        var result = false
        val listOrder: List<OrderDomain> = orderRepository.getListOrder(userId)

        listOrder.forEach {
            if ((it.productID == productId && it.userId == userId)) {
                result = true
                return@forEach
            } else result = false
        }
        return result
    }
}