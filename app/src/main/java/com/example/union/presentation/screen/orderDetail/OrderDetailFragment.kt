package com.example.union.presentation.screen.orderDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.databinding.OrderDetailFragmentBinding
import com.example.union.presentation.APP

class OrderDetailFragment : Fragment() {

    lateinit var binding: OrderDetailFragmentBinding
    private lateinit var viewModel: OrderDetailViewModel
    lateinit var currentProductDomain: ProductDomain
    lateinit var currentOrder: OrderDomain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentProductDomain = arguments?.getSerializable("product") as ProductDomain
        currentOrder = arguments?.getSerializable("order") as OrderDomain
        binding = OrderDetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)
        displayOrder()

        binding.tvLink.setOnClickListener {
            goToLink()
        }

        binding.btnBack.setOnClickListener {
            navigateToProfileFragment()
        }
    }

    private fun navigateToProfileFragment() {
        APP.navController.navigate(R.id.action_orderDetailFragment_to_profileFragment)
    }

    private fun goToLink() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentProductDomain.productLink))
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun displayOrder() {
        binding.run {
            tvNameProduct.text = currentProductDomain.productName
            Glide
                .with(APP)
                .load(currentProductDomain.productPhoto)
                .error(R.drawable.ic_empty_photo)
                .into(ivProductPhoto)
            tvPrice.text = "${currentProductDomain.productPrice} $ за шт"
            tvAmount.text =
                "Осталось: ${
                    currentProductDomain.totalAmount.let {
                        currentProductDomain.amount.minus(
                            it
                        )
                    }
                }"
            tvCity.text = "Город: ${currentProductDomain.city}"
            tvLink.text = currentProductDomain.productLink
            tvTotalAmount.text = "Вы берете: ${currentOrder.totalAmount}"
        }
    }

}