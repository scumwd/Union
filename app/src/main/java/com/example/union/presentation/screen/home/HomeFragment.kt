package com.example.union.presentation.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Product
import com.example.union.adapter.ProductAdapter
import com.example.union.databinding.HomeFragmentBinding
import com.example.union.presentation.APP

class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.rvProduct
        val adapter: ProductAdapter? = context?.let { ProductAdapter(it) }
        recyclerView.adapter = adapter
        viewModel.getAppProduct().observe(viewLifecycleOwner,{listProduct ->
            adapter?.setList(listProduct.asReversed())
        })

    }

    companion object{
        fun clickProduct(product: Product){
            val bundle = Bundle()
            bundle.putSerializable("product",product)
            //APP.navController.

        }
    }

}