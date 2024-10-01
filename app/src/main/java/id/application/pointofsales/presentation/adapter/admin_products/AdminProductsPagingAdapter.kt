package id.application.pointofsales.presentation.adapter.admin_products

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.application.core.data.network.model.products.ItemAllProducts
import id.application.pointofsales.databinding.ItemAdminProductsBinding
import id.application.pointofsales.utils.Utils.formatRupiah

class AdminProductsPagingAdapter(
    private val onClickListener: (ItemAllProducts) -> Unit,
) : PagingDataAdapter<ItemAllProducts, AdminProductsPagingAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemAllProducts>() {
            override fun areItemsTheSame(
                oldItem: ItemAllProducts,
                newItem: ItemAllProducts
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ItemAllProducts,
                newItem: ItemAllProducts
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAdminProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    inner class ItemViewHolder(
        private val binding: ItemAdminProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "NewApi")
        fun bind(item: ItemAllProducts) {
            with(binding){
//                ivUrl.load(item.url)
                tvNameProduct.text = item.name
                tvTypeProduct.text = item.categoryName
                tvHargaJual.text = formatRupiah(item.price.toInt())
                btnAddCart.setOnClickListener {
                    onClickListener.invoke(item)
                }
            }
        }
    }
}
