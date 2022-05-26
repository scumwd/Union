package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.OrderData
import com.example.data.storage.order.OrderDao
import com.example.data.storage.product.ProductDao
import com.example.domain.models.OrderDomain
import com.example.domain.repository.OrderRepository

class OrderRepositoryImpl(private val orderDao: OrderDao, private val userId: String) :
    OrderRepository {

    override suspend fun getListOrder(): List<OrderDomain> {
        return orderDao.getListOrder(userId).map { orderLocal ->
            OrderDomain(
                productID = orderLocal.productId,
                userId = orderLocal.userId,
                totalAmount = orderLocal.totalAmount
            )
        }
    }

    override val allOrderDomain: LiveData<List<OrderDomain>>
        get() = orderDao.getOrders(userId).map { list ->
            list.map { orderLocal ->
                OrderDomain(
                    productID = orderLocal.productId,
                    userId = orderLocal.userId,
                    totalAmount = orderLocal.totalAmount
                )
            }
        }

    override suspend fun insertOrder(orderDomain: OrderDomain, onSuccess: () -> Unit) {
        val orderDb = OrderData(
            productId = orderDomain.productID,
            userId = orderDomain.userId,
            totalAmount = orderDomain.totalAmount
        )
        orderDao.insert(orderDb)
        onSuccess()
    }

    override suspend fun deleteOrder(orderDomain: OrderDomain, onSuccess: () -> Unit) {
        val orderDb = OrderData(
            productId = orderDomain.productID,
            userId = orderDomain.userId,
            totalAmount = orderDomain.totalAmount
        )
        orderDao.delete(orderDb)
        onSuccess()
    }
}