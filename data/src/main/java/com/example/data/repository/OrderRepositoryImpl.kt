package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.models.OrderCloud
import com.example.data.models.OrderRoom
import com.example.data.storage.order.OrderDao
import com.example.data.models.OrderCloudData
import com.example.data.storage.order.OrderFirebase
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.domain.repository.OrderRepository
import com.google.firebase.auth.FirebaseAuth

class OrderRepositoryImpl(
    private val orderDao: OrderDao,
    private val orderFirebase: OrderFirebase
) :
    OrderRepository {

    private lateinit var mAuth: FirebaseAuth

    override suspend fun getListOrder(productId: String): OrderDomain? {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }
        val orderRoom = orderDao.getListOrder(userId, productId)
        val orderDomain: OrderDomain? = orderRoom?.let {
            OrderDomain(
                productID = orderRoom.productId,
                userId = it.userId,
                totalAmount = orderRoom.totalAmount
            )
        }
        return orderDomain
    }


    override fun allOrderDomain(): LiveData<List<OrderDomain>> {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }

        return orderDao.getOrders(userId).map { list ->
            list.map { orderLocal ->
                OrderDomain(
                    productID = orderLocal.productId,
                    userId = orderLocal.userId,
                    totalAmount = orderLocal.totalAmount
                )
            }
        }
    }

    override fun insertOrder(listCloud: List<OrderDomain?>, onSuccess: () -> Unit) {
        var listRoom: List<OrderRoom?> = mutableListOf()
        listCloud.forEach { cloud ->
            val orderData = cloud?.userId?.let {
                OrderRoom(
                    productId = cloud.productID,
                    userId = it,
                    totalAmount = cloud.totalAmount
                )
            }
            listRoom = listRoom + orderData
        }
        listRoom.forEach {
            if (it != null) {
                orderDao.insert(it)
            }
        }

        onSuccess()
    }


    override suspend fun insertOrderFireBase(orderDomain: OrderDomain) {
        mAuth = FirebaseAuth.getInstance()
        val userId: String
        mAuth.currentUser?.uid.let { userId = it.toString() }

        orderFirebase.insertOrder(OrderCloud(
            userId = userId,
            productID = orderDomain.productID,
            totalAmount = orderDomain.totalAmount
        ))
    }

    override suspend fun getOrdersFirebase(): List<OrderDomain> {
        val cloudOrder = orderFirebase.getOrders()
        val listDomain: List<OrderDomain> = cloudOrder.map { cloud ->
            OrderDomain(
                productID = cloud?.getProductId().toString(),
                userId = cloud?.getUserId().toString(),
                totalAmount = cloud?.getTotalAmount().toString().toInt()

            )
        }
        return listDomain
    }
}