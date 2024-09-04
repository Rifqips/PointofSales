package id.application.pointofsales.presentation.feature.home

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.application.pointofsales.R
import id.application.pointofsales.databinding.FragmentHomeBinding
import id.application.pointofsales.presentation.adapter.BouquetAdapter
import id.application.pointofsales.presentation.adapter.BouquetItem
import id.application.pointofsales.presentation.adapter.ProductAdapter
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, VmApplication>(FragmentHomeBinding::inflate) {

    override val viewModel: VmApplication by viewModel()
    private lateinit var adapter: BouquetAdapter
    private lateinit var adapterProduct: ProductAdapter

    private val allProducts = listOf(
        BouquetItem("https://i.pinimg.com/736x/c7/26/40/c72640b4c7fc07895b392c98755a1164.jpg","Bouquet A", 10000, 5000, 2000, 17000),
        BouquetItem("https://i.pinimg.com/originals/d8/2a/cd/d82acd6891fc8cc98dfbd4db9604e53c.jpg","Bouquet B", 15000, 6000, 3000, 24000),
        BouquetItem("https://i.pinimg.com/236x/bb/a6/4e/bba64e0a760b5ea6fe1b13ca34244467.jpg","Bouquet B", 25000, 6000, 3000, 76000),
        BouquetItem("https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg","Bouquet C", 45000, 6000, 3000, 56000),
        BouquetItem("https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg","Bouquet D", 75000, 6000, 3000, 44000),
        BouquetItem("https://i.pinimg.com/236x/bb/a6/4e/bba64e0a760b5ea6fe1b13ca34244467.jpg","Bouquet B", 25000, 6000, 3000, 76000),
        BouquetItem("https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg","Bouquet C", 45000, 6000, 3000, 56000),
        BouquetItem("https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg","Bouquet D", 75000, 6000, 3000, 44000),
    )

    private val bouquetProducts = allProducts.filter { it.name.contains("Bouquet", ignoreCase = true) }
    private val bahanBouquetProducts = allProducts.filter { it.name.contains("Bahan", ignoreCase = true) }
    private val accessoriesProducts = allProducts.filter { it.name.contains("Accessories", ignoreCase = true) }

    override fun initView() {
        adapter = BouquetAdapter(allProducts)
        adapterProduct = ProductAdapter(allProducts)
        with(binding){
            rvListProduct.layoutManager = GridLayoutManager(context, 3)
            rvListProduct.adapter = adapter
            rvTotalOrder.layoutManager = LinearLayoutManager(context)
            rvTotalOrder.adapter = adapterProduct
        }
    }


    override fun initListener() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chip_all -> adapter.updateData(allProducts)
                R.id.chip_bouqet -> adapter.updateData(bouquetProducts)
                R.id.chip_bouqet_materials -> adapter.updateData(bahanBouquetProducts)
                R.id.chip_bouqet_accesories -> adapter.updateData(accessoriesProducts)
            }
        }
    }
}
