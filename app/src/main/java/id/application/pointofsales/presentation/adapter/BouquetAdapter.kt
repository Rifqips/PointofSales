package id.application.pointofsales.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.pointofsales.databinding.ItemBouquetBinding

class BouquetAdapter(private var items: List<BouquetItem>) : RecyclerView.Adapter<BouquetAdapter.BouquetViewHolder>() {

    inner class BouquetViewHolder(val binding: ItemBouquetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BouquetItem) {
            binding.ivUrl.load(item.url)
            binding.tvBouquetName.text = item.name
            binding.tvBahan.text = "Bahan: Rp ${item.bahan}"
            binding.tvJasa.text = "Jasa: Rp ${item.jasa}"
            binding.tvLaba.text = "Laba: Rp ${item.laba}"
            binding.tvHargaJual.text = "Harga Jual: Rp ${item.hargaJual}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BouquetViewHolder {
        val binding = ItemBouquetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BouquetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BouquetViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<BouquetItem>) {
        items = newItems
        notifyDataSetChanged()
    }

}

data class BouquetItem(
    val url: String,
    val name: String,
    val bahan: Int,  // Rp
    val jasa: Int,   // Rp
    val laba: Int,   // Rp
    val hargaJual: Int // Rp
)

