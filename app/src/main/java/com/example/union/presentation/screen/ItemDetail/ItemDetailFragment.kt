package com.example.union.presentation.screen.ItemDetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.domain.models.Product
import com.example.union.R
import com.example.union.databinding.ItemDetailFragmentBinding
import com.example.union.presentation.APP

class ItemDetailFragment : Fragment() {

    private lateinit var viewModel: ItemDetailViewModel
    lateinit var binding: ItemDetailFragmentBinding
    lateinit var currentProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemDetailFragmentBinding.inflate(layoutInflater, container,false)
        currentProduct = arguments?.getSerializable("product") as Product
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        binding.run {
            tvNameProduct.text = currentProduct.productName
            Glide
                .with(APP)
                .load(currentProduct.productPhoto)
                .into(ivProductPhoto)
            tvPrice.text = "${currentProduct.productPrice} $ за шт"
            tvAmount.text = "Осталось: ${currentProduct.amount - currentProduct.totalAmount}"
            tvCity.text = "Город: ${currentProduct.city}"
            tvLink.text = currentProduct.productLink
            btnBack.setOnClickListener{
                APP.navController.navigate(R.id.action_itemDetailFragment_to_homeFragment)
            }
        }
    }


}