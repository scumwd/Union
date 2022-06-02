package com.example.union.presentation.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.adapter.ProductAdapter
import com.example.union.app.App
import com.example.union.databinding.HomeFragmentBinding
import com.example.union.presentation.APP
import com.example.union.presentation.MainActivity
import com.example.union.presentation.screen.addItem.AddItemViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        (activity as MainActivity).factory
    }

    lateinit var recyclerView: RecyclerView
    lateinit var binding: HomeFragmentBinding
    lateinit var adapter: ProductAdapter

    companion object {
        fun clickProduct(productDomain: ProductDomain) {
            val bundle = Bundle()
            bundle.putSerializable("product", productDomain)
            APP.navController.navigate(R.id.action_homeFragment_to_itemDetailFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel.getUser()
        viewModel.getProductsFromFireBase()
        recyclerView = binding.rvProduct
        adapter = ProductAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllProduct().observe(viewLifecycleOwner, { listProduct ->
            adapter.setList(listProduct.asReversed())
        })
    }
}