package id.application.pointofsales.presentation.adapter.admin_products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.pointofsales.databinding.ItemAdminProductsBinding
import id.application.pointofsales.presentation.adapter.cashier.BouquetItem
import id.application.pointofsales.utils.Utils.formatRupiah

class AdminProductsAdapter(
    private var items: List<BouquetItem>,
    private var onClick: (BouquetItem) -> Unit
) : RecyclerView.Adapter<AdminProductsAdapter.ProductsViewHolder>() {

    inner class ProductsViewHolder(val binding: ItemAdminProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind (item : BouquetItem){
                with(binding){
                    ivUrl.load(item.url)
                    tvNameProduct.text = item.name
                    tvTypeProduct.text = "Bouqet"
                    tvHargaJual.text = formatRupiah(item.hargaJual)
                    btnAddCart.setOnClickListener {
                        onClick.invoke(item)
                    }

                }
            }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminProductsAdapter.ProductsViewHolder {
        val binding = ItemAdminProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminProductsAdapter.ProductsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}