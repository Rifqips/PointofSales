package id.application.pointofsales.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.pointofsales.databinding.ItemCartProductBinding

class ProductAdapter(private var items: List<BouquetItem>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemCartProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BouquetItem) {
            binding.ivProduct.load(item.url)
            binding.tvDescripionProduct.text = item.name
            binding.tvTitleStorage.text = "Bahan: Rp ${item.bahan}"
            binding.tvProductPrice.text = "Harga Jual: Rp ${item.hargaJual}"
            var count = 1
            binding.tvCountNumber.text = count.toString()
            binding.tvMinus.setOnClickListener {
                if (count > 1) {
                    count--
                    binding.tvCountNumber.text = count.toString()
                }
            }
            binding.tvPlus.setOnClickListener {
                count++
                binding.tvCountNumber.text = count.toString()
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

    fun updateData(newItems: List<BouquetItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}

