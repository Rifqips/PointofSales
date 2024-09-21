package id.application.pointofsales.presentation.feature.admin_products

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.GridLayoutManager
import id.application.core.data.local.database.cart.CartKeys
import id.application.pointofsales.databinding.DialogAdminCreateProductsBinding
import id.application.pointofsales.databinding.FragmentAdminProductsBinding
import id.application.pointofsales.presentation.adapter.admin_products.AdminProductsAdapter
import id.application.pointofsales.presentation.adapter.cashier.BouquetItem
import id.application.pointofsales.presentation.viewmodel.VmRoomApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminProductsFragment : BaseFragment<FragmentAdminProductsBinding, VmRoomApplication>(
    FragmentAdminProductsBinding::inflate) {

    private val allProducts = listOf(
        BouquetItem(
            "https://i.pinimg.com/736x/c7/26/40/c72640b4c7fc07895b392c98755a1164.jpg",
            "Bouquet A",
            10000,
            5000,
            2000,
            17000
        ),
        BouquetItem(
            "https://i.pinimg.com/originals/d8/2a/cd/d82acd6891fc8cc98dfbd4db9604e53c.jpg",
            "Bouquet B",
            15000,
            6000,
            3000,
            24000
        ),
        BouquetItem(
            "https://i.pinimg.com/236x/bb/a6/4e/bba64e0a760b5ea6fe1b13ca34244467.jpg",
            "Bouquet B",
            25000,
            6000,
            3000,
            76000
        ),
        BouquetItem(
            "https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg",
            "Bouquet C",
            45000,
            6000,
            3000,
            56000
        ),
        BouquetItem(
            "https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg",
            "Bouquet D",
            75000,
            6000,
            3000,
            44000
        ),
        BouquetItem(
            "https://i.pinimg.com/236x/bb/a6/4e/bba64e0a760b5ea6fe1b13ca34244467.jpg",
            "Bouquet B",
            25000,
            6000,
            3000,
            76000
        ),
        BouquetItem(
            "https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg",
            "Bouquet C",
            45000,
            6000,
            3000,
            56000
        ),
        BouquetItem(
            "https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg",
            "Bouquet D",
            75000,
            6000,
            3000,
            44000
        ),
    )
    private lateinit var adapter: AdminProductsAdapter

    override val viewModel: VmRoomApplication by viewModel()

    override fun initView() {
        setUpRv()
    }

    override fun initListener() {
        with(binding){
            btnCreateProducts.setOnClickListener {
                dialogCreateProducts()
            }
        }
    }

    private fun setUpRv(){
        adapter = AdminProductsAdapter(allProducts) {
            val cartKey = CartKeys(
                jasa = it.jasa,
                laba = it.laba,
                name = it.name,
                bahan = it.bahan,
                quantity = 1,
                hargaJual = it.hargaJual,
                url = it.url
            )
            viewModel.insertCart(cartKey)
        }
        binding.rvListProducts.layoutManager = GridLayoutManager(context, 3)
        binding.rvListProducts.adapter = adapter

    }

    private var activeDialog : AlertDialog? = null

    @SuppressLint("ClickableViewAccessibility")
    private fun dialogCreateProducts(){
        activeDialog?.let {
            if (it.isShowing){
                it.dismiss()
            }
        }
        val binding : DialogAdminCreateProductsBinding = DialogAdminCreateProductsBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext(), 0).create()
        dialog.apply {
            setView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }.show()
        activeDialog = dialog
        binding.ivClose.setOnClickListener {
            dialog.dismiss()
            activeDialog = null
        }
        binding.root.setOnTouchListener{ _, _ ->
            true
        }
    }

}