package id.application.pointofsales.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.core.data.local.database.cart.CartKeys
import id.application.pointofsales.databinding.ItemCartProductBinding

class ProductAdapter(
    private var items: List<CartKeys>,
    private var onDelete : (CartKeys) -> Unit,
    private val itemSelectedPlus : (CartKeys) -> Unit,
    private val itemSelectedMinus : (CartKeys) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemCartProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartKeys) {
            with(binding){
                ivProduct.load(item.url)
                tvDescripionProduct.text = item.name
                tvTitleStorage.text = "Bahan: Rp ${item.bahan}"

                tvCountNumber.text = item.quantity.toString()
                tvMinus.setOnClickListener {
                    tvCountNumber.text = item.quantity.toString()
                    if (item.quantity > 1) {
                        itemSelectedMinus.invoke(item)
                    }
                }
                tvPlus.setOnClickListener {
                    tvCountNumber.text = item.quantity.toString()
                    itemSelectedPlus.invoke(item)
                }
                tvProductPrice.text = item.hargaJual.toString()

                ivDelete.setOnClickListener {
                    onDelete.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setlistCart(listCart: List<CartKeys>){
        this.items = listCart
        notifyDataSetChanged()
    }
}

