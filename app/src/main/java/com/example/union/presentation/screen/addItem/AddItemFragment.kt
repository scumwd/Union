package com.example.union.presentation.screen.addItem


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.domain.models.ProductDomain
import com.example.union.R
import com.example.union.databinding.AddItemFragmentBinding
import com.example.union.presentation.APP
import kotlinx.android.synthetic.main.add_item_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddItemFragment : Fragment() {

    lateinit var binding: AddItemFragmentBinding
    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddItemFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        var productName: String
        var productLink: String
        var productPhoto: String
        var productAmount: Int
        var productCity: String
        var productPrice: Int
        var productDomain: ProductDomain
        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
        binding.btnSend.setOnClickListener {
            binding.run {
                if (ed_productName.text.toString().isEmpty()
                    || ed_productLink.text.toString().isEmpty()
                    || ed_productPhoto.text.toString().isEmpty()
                    || ed_productAmount.text.toString().isEmpty()
                    || ed_productCity.text.toString().isEmpty()
                    || ed_productPrice.text.toString().isEmpty()
                )
                    Toast.makeText(APP, "Пожалуйста, заполните все поля.", Toast.LENGTH_SHORT)
                        .show()
                else {
                    if (Patterns.WEB_URL.matcher(ed_productLink.text.toString()).matches()
                        &&
                        Patterns.WEB_URL.matcher(ed_productPhoto.text.toString()).matches()
                    ) {
                        viewModel.chekProduct(ed_productLink.text.toString())
                            .observe(
                                viewLifecycleOwner,
                                { listProduct ->
                                    if (listProduct != null && listProduct.city == ed_productCity.text.toString()
                                    ) {
                                        Toast.makeText(
                                            APP,
                                            "Данный товар уже опубликован.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        val bundle = Bundle()
                                        bundle.putSerializable(
                                            "product",
                                            listProduct
                                        )
                                        APP.navController.navigate(
                                            R.id.action_addItemFragment_to_itemDetailFragment,
                                            bundle
                                        )
                                    } else {
                                        productName = ed_productName.text.toString()
                                        productLink = ed_productLink.text.toString()
                                        productPhoto = ed_productPhoto.text.toString()
                                        productAmount =
                                            edProductAmount.text.toString().toInt()
                                        productCity = ed_productCity.text.toString()
                                        productPrice =
                                            edProductPrice.text.toString().toInt()
                                        productDomain = ProductDomain(
                                            productName = productName,
                                            productLink = productLink,
                                            productPhoto = productPhoto,
                                            productPrice = productPrice,
                                            city = productCity,
                                            amount = productAmount
                                        )
                                        //viewModel.insert(productDomain) {}
                                        viewModel.insertInFireBase(productDomain)
                                        Toast.makeText(
                                            APP,
                                            "Товар опубликован успешно.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                })

                    } else
                        Toast.makeText(
                            context,
                            "Введенные ссылки не корректны.",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                }

            }
        }
    }

}


