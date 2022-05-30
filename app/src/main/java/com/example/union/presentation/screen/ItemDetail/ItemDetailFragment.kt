package com.example.union.presentation.screen.ItemDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.domain.update.UpdateProductInFireBase
import com.example.union.R
import com.example.union.app.App
import com.example.union.databinding.ItemDetailFragmentBinding
import com.example.union.presentation.APP
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import javax.inject.Inject

class ItemDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ItemDetailViewModelFactory

    lateinit var binding: ItemDetailFragmentBinding
    lateinit var currentProductDomain: ProductDomain
    private lateinit var viewModel: ItemDetailViewModel
    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemDetailFragmentBinding.inflate(layoutInflater, container, false)
        currentProductDomain = arguments?.getSerializable("product") as ProductDomain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (APP.applicationContext as App).appComponent.inject(this)

        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        viewModel = ViewModelProvider(this,viewModelFactory)[ItemDetailViewModel::class.java]
        binding.run {

            displayProduct()

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

    private fun goToLink() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentProductDomain.productLink))
            startActivity(intent)
        }
        catch (e: Exception){
            Toast.makeText(APP, "Ссылка недействительна.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buyProduct() {
        viewModel.getOrder()
        lifecycleScope.launch(Dispatchers.IO) {
            if (currentProductDomain.productID.let { viewModel.checkOrder(it) }) {
                withContext(lifecycleScope.coroutineContext) {
                    Toast.makeText(
                        APP,
                        "Вы уже участвуете в покупке.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                insertOrderIntoDb()
                val totalAmount =
                    binding.edTotalAmount.text.toString().toInt() + currentProductDomain.totalAmount
                val productDomain = ProductDomain(
                    productID = currentProductDomain.productID,
                    productName = currentProductDomain.productName,
                    productLink = currentProductDomain.productLink,
                    productPrice = currentProductDomain.productPrice,
                    amount = currentProductDomain.amount,
                    totalAmount = totalAmount,
                    city = currentProductDomain.city,
                    productPhoto = currentProductDomain.productPhoto
                )
                viewModel.update(productDomain)
                withContext(lifecycleScope.coroutineContext) {
                    Toast.makeText(
                        APP,
                        "Вы успешно зарезервировали товар.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun displayProduct() {
        binding.run {
            tvNameProduct.text = currentProductDomain.productName
            Glide
                .with(APP)
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
        APP.navController.navigate(R.id.action_itemDetailFragment_to_homeFragment)
    }

    private fun insertOrderIntoDb() {
        mAuth = FirebaseAuth.getInstance()
        val userId = mAuth.currentUser?.uid
        val totalAmountBuy: Int = binding.edTotalAmount.text.toString().toInt()
        val orderDomain = userId?.let { it1 ->
            currentProductDomain.productID.let {
                OrderDomain(
                    productID = it,
                    userId = it1,
                    totalAmount = totalAmountBuy
                )
            }
        }
        if (orderDomain != null) {
            viewModel.insert(orderDomain)
        }
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