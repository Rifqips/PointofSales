package id.application.pointofsales.presentation.feature.product

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import id.application.pointofsales.databinding.DialogDetailStatusProductBinding
import id.application.pointofsales.databinding.FragmentProductBinding
import id.application.pointofsales.presentation.adapter.product.ProductStatus
import id.application.pointofsales.presentation.adapter.product.ProductStatusAdapter
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment :
    BaseFragment<FragmentProductBinding, VmApplication>(FragmentProductBinding::inflate) {

    override val viewModel: VmApplication by viewModel()
    private lateinit var productAdapter: ProductStatusAdapter

    override fun initView() {
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        val productList = listOf(
                ProductStatus(
                imageUrl = "https://i.pinimg.com/736x/c7/26/40/c72640b4c7fc07895b392c98755a1164.jpg",
                productName = "Bouquet",
                bouquetName = "Rose Bouquet",
                status = "Lunas",
                datetime = "10-10-2024",
                salePrice = "Rp.100.000,00"
            ),
                ProductStatus(
                imageUrl = "https://i.pinimg.com/originals/d8/2a/cd/d82acd6891fc8cc98dfbd4db9604e53c.jpg",
                productName = "Bouquet",
                bouquetName = "Rose Bouquet",
                status = "Belum Lunas",
                datetime = "10-10-2024",
                salePrice = "Rp.100.000,00"
            ),
                ProductStatus(
                imageUrl = "https://i.pinimg.com/236x/bb/a6/4e/bba64e0a760b5ea6fe1b13ca34244467.jpg",
                productName = "Bouquet",
                bouquetName = "Rose Bouquet",
                status = "Lunas",
                datetime = "10-10-2024",
                salePrice = "Rp.100.000,00"
            ),
                ProductStatus(
                imageUrl = "https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg",
                productName = "Bouquet",
                bouquetName = "Rose Bouquet",
                status = "Belum Lunas",
                datetime = "10-10-2024",
                salePrice = "Rp.100.000,00"
            ),
        )

        productAdapter = ProductStatusAdapter(productList) { product ->
            showDialogConfirmSaveData()
        }
        binding.rvProductStatus.adapter = productAdapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvProductStatus.layoutManager = gridLayoutManager
    }


    override fun initListener() {
        // Set listeners for other views like search, filter etc.
    }


    private var activeDialog: AlertDialog? = null

    private fun showDialogConfirmSaveData() {
        activeDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        val binding: DialogDetailStatusProductBinding = DialogDetailStatusProductBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext(), 0).create()
        dialog.apply {
            setView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
        }.show()
        activeDialog = dialog


        with(binding) {
            ivUrl.load("https://i.pinimg.com/736x/c7/26/40/c72640b4c7fc07895b392c98755a1164.jpg")
            ivClose.setOnClickListener {
                dialog.dismiss()
                activeDialog = null
            }
        }
        binding.root.setOnTouchListener { _, _ ->
            true
        }
    }

}
