package id.application.pointofsales.presentation.adapter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.pointofsales.R
import id.application.pointofsales.databinding.ItemProductStatusBinding


class ProductStatusAdapter(
    private val productList: List<ProductStatus>,
    private val onClick: (ProductStatus) -> Unit
) : RecyclerView.Adapter<ProductStatusAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductStatus) {
            binding.ivUrl.load(product.imageUrl)

            binding.tvProduct.text = product.productName
            binding.tvBouquetName.text = product.bouquetName
            binding.tvDatetime.text = product.datetime
            binding.tvHargaJual.text = product.salePrice
            binding.tvStatus.text = product.status
            if (product.status == "Belum Lunas") {
                binding.cvStatus.backgroundTintList =
                    ContextCompat.getColorStateList(itemView.context, R.color.light_yellow)
            }

            binding.btnAddCart.setOnClickListener {
                onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductStatusBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

data class ProductStatus(
    val imageUrl: String,
    val productName: String,
    val bouquetName: String,
    val status: String,
    val datetime: String,
    val salePrice: String
)

