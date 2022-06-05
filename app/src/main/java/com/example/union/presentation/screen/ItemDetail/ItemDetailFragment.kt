package com.example.union.presentation.screen.ItemDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.OrderDomain
import com.example.domain.models.OrdersWithUsers
import com.example.domain.models.ProductDomain
import com.example.domain.models.UserWithUID
import com.example.union.R
import com.example.union.adapter.UserAdapter
import com.example.union.app.App
import com.example.union.databinding.ItemDetailFragmentBinding
import com.example.union.presentation.MainActivity
import kotlinx.coroutines.*

class ItemDetailFragment : Fragment() {

    private val viewModel: ItemDetailViewModel by viewModels {
        (activity as MainActivity).factory
    }

    lateinit var binding: ItemDetailFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: UserAdapter
    lateinit var currentProductDomain: ProductDomain
    lateinit var emptyRecyclerView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemDetailFragmentBinding.inflate(layoutInflater, container, false)
        currentProductDomain = arguments?.getSerializable("product") as ProductDomain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as App).appComponent.inject(this)

        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        binding.run {

            recyclerView = rvUsers
            adapter = UserAdapter()
            recyclerView.adapter = adapter
            emptyRecyclerView = binding.emptyRvItem

            displayProduct()

            displayUsers()

            tvLink.setOnClickListener {
                goToLink()
            }

            btnBack.setOnClickListener {
                navigateToHomeFragment()
            }

            btnBuy.setOnClickListener {
                buyProduct()
            }

            btnMinus.setOnClickListener {
                subtractTotalAmount()
            }

            btnPlus.setOnClickListener {
                addTotalAmount()
            }
        }
    }

    private fun displayUsers() {
        lifecycleScope.launch {
            val orderList = viewModel.getOrderByProductLink(currentProductDomain.productID)
            if (orderList.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                emptyRecyclerView.visibility = View.GONE
                adapter.setList(orderList)
            } else {
                emptyRecyclerView.visibility = View.VISIBLE
            }
        }

    }

    private fun goToLink() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentProductDomain.productLink))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Ссылка недействительна.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buyProduct() {
        lifecycleScope.launch(Dispatchers.IO) {
            if (viewModel.getOrder()) {
                if (currentProductDomain.productID.let { viewModel.checkOrder(it) }) {
                    withContext(lifecycleScope.coroutineContext) {
                        Toast.makeText(
                            requireContext(),
                            "Вы уже участвуете в покупке.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    insertOrderIntoDb()
                    currentProductDomain.totalAmount =
                        binding.edTotalAmount.text.toString()
                            .toInt() + currentProductDomain.totalAmount
                    val productDomain = ProductDomain(
                        productID = currentProductDomain.productID,
                        productName = currentProductDomain.productName,
                        productLink = currentProductDomain.productLink,
                        productPrice = currentProductDomain.productPrice,
                        amount = currentProductDomain.amount,
                        totalAmount = currentProductDomain.totalAmount,
                        city = currentProductDomain.city,
                        productPhoto = currentProductDomain.productPhoto
                    )
                    withContext(lifecycleScope.coroutineContext) {
                        displayProduct()
                    }
                    viewModel.update(productDomain)
                    withContext(lifecycleScope.coroutineContext) {
                        Toast.makeText(
                            requireContext(),
                            "Вы успешно зарезервировали товар.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (viewModel.getOrder())
                        displayUsers()
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun displayProduct() {
        binding.run {
            tvNameProduct.text = currentProductDomain.productName
            Glide
                .with(requireContext())
                .load(currentProductDomain.productPhoto)
                .error(R.drawable.ic_empty_photo)
                .into(ivProductPhoto)
            tvPrice.text = "${currentProductDomain.productPrice} $ за шт"
            tvAmount.text =
                "Осталось: ${currentProductDomain.amount.minus(currentProductDomain.totalAmount)}"
            tvCity.text = "Город: ${currentProductDomain.city}"
            tvLink.text = currentProductDomain.productLink
        }
    }

    private fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_itemDetailFragment_to_homeFragment)
    }

    private fun insertOrderIntoDb() {
        val totalAmountBuy: Int = binding.edTotalAmount.text.toString().toInt()
        val orderDomain = OrderDomain(
            userId = "",
            productID = currentProductDomain.productID,
            totalAmount = totalAmountBuy
        )
        viewModel.insert(orderDomain)
    }

    private fun subtractTotalAmount() {
        var totalAmount = binding.edTotalAmount.text.toString().toInt()
        if (totalAmount > 1)
            totalAmount -= 1
        binding.edTotalAmount.setText(totalAmount.toString())
    }

    private fun addTotalAmount() {
        var totalAmount = binding.edTotalAmount.text.toString().toInt()
        totalAmount += 1
        binding.edTotalAmount.setText(totalAmount.toString())
    }

}