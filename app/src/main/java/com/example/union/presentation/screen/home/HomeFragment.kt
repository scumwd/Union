package com.example.union.presentation.screen.home

import android.R
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.text.toLowerCase
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ProductDomain
import com.example.union.adapter.ProductAdapter
import com.example.union.databinding.HomeFragmentBinding
import com.example.union.presentation.MainActivity
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        (activity as MainActivity).factory
    }

    lateinit var recyclerView: RecyclerView
    lateinit var binding: HomeFragmentBinding
    lateinit var adapter: ProductAdapter
    lateinit var searchView: SearchView
    lateinit var emptyRecyclerView: TextView

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
        searchView = binding.searchView
        searchView.clearFocus()
        emptyRecyclerView = binding.emptyRv
        recyclerView = binding.rvProduct
        adapter = ProductAdapter(requireContext())
        recyclerView.adapter = adapter

        val pd = Dialog(requireContext(), R.style.Theme_Black)
        val view: View =
            LayoutInflater.from(context).inflate(com.example.union.R.layout.progressbar, null)
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE)
        pd.window?.setBackgroundDrawableResource(com.example.union.R.color.transparent)
        pd.setContentView(view)
        pd.show()

            lifecycleScope.launch {
                if (viewModel.getProductsFromFireBase() && viewModel.getUserFirebase()) {

                    viewModel.getAllProduct().observe(viewLifecycleOwner, { listProduct ->
                        if (listProduct.isNotEmpty()) {
                            recyclerView.visibility = View.VISIBLE
                            emptyRecyclerView.visibility = View.GONE
                            adapter.setList(listProduct.asReversed())

                        }
                        else{
                            emptyRecyclerView.visibility = View.VISIBLE
                        }
                        pd.hide()
                    })

                }
            }


            searchView.setOnClickListener {
                searchView.onActionViewExpanded()
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filter(newText)
                    return true
                }

            })
        }

        private fun filter(newText: String?) {
            viewModel.getAllProduct().observe(viewLifecycleOwner, { listProduct ->
                val filteredList: MutableList<ProductDomain> = ArrayList()
                for (item in listProduct) {
                    if (newText != null) {
                        if (item.productName.lowercase(Locale.getDefault()).contains(
                                newText.lowercase(
                                    Locale.getDefault()
                                )
                            )
                        ) {
                            filteredList.add(item)
                        }
                    }
                }
                if (filteredList.isEmpty())
                    Toast.makeText(requireContext(), "Ничего не найдено", Toast.LENGTH_SHORT).show()
                else
                    adapter.setFiltered(filteredList)
            })
        }
    }