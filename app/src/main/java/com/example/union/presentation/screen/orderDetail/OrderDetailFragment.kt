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
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.adapter.UserAdapter
import com.example.union.app.App
import com.example.union.databinding.OrderDetailFragmentBinding
import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.addItem.AddItemViewModel
import kotlinx.coroutines.launch

class OrderDetailFragment : Fragment() {

    lateinit var binding: OrderDetailFragmentBinding

    private val viewModel: OrderDetailViewModel by viewModels {
        (activity as MainActivity).factory
    }
    lateinit var currentProductDomain: ProductDomain
    lateinit var currentOrder: OrderDomain
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: UserAdapter
    lateinit var emptyRecyclerView: TextView

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

        (requireContext().applicationContext as App).appComponent.inject(this)

        init()
    }

    private fun init() {
        recyclerView = binding.rvUsers
        adapter = UserAdapter()
        recyclerView.adapter = adapter

        emptyRecyclerView = binding.emptyRvItem

        displayOrder()
        displayUsers()

        binding.tvLink.setOnClickListener {
            goToLink()
        }

        binding.btnBack.setOnClickListener {
            navigateToProfileFragment()
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

    private fun navigateToProfileFragment() {
        findNavController().navigate(R.id.action_orderDetailFragment_to_profileFragment)
    }

    private fun goToLink() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(currentProductDomain.productLink))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Ссылка недействительна.", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayOrder() {
        binding.run {
            tvNameProduct.text = currentProductDomain.productName
            Glide
                .with(requireContext())
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