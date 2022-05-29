package com.example.union.presentation.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.OrderDomain
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.adapter.OrderAdapter
import com.example.union.databinding.ProfileFragmentBinding
import com.example.union.presentation.APP
import com.example.union.presentation.screen.login.AuthenticationActivity

class ProfileFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding: ProfileFragmentBinding
    private lateinit var adapter: OrderAdapter

    companion object {
        fun clickProduct(productDomain: ProductDomain, orderDomain: OrderDomain) {
            val bundle = Bundle()
            bundle.putSerializable("product", productDomain)
            bundle.putSerializable("order", orderDomain)
            APP.navController.navigate(R.id.action_profileFragment_to_orderDetailFragment, bundle)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.initDatabase()
        viewModel.getOrder()
        recyclerView = binding.rvOrder
        adapter = OrderAdapter()
        recyclerView.adapter = adapter

        viewModel.getAllOrder().observe(viewLifecycleOwner, { localListOrder ->
            viewModel.getAllProduct().observe(viewLifecycleOwner, { localListProduct ->
                adapter.setList(listProduct = localListProduct, listOrder = localListOrder)
                binding.tvCountBuy.text = "Кол-во покупок: ${localListOrder.size}"
            })
        })

        viewModel.getUser().observe(viewLifecycleOwner, { user ->
            binding.tvName.text = "${user.firstName} ${user.lastName}"
        })

        binding.btnSignOut.setOnClickListener {
            viewModel.signOut()
            val intent = Intent(this.context, AuthenticationActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}