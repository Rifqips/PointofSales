package id.application.pointofsales.presentation.adapter.cashier

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.pointofsales.databinding.ItemBouquetBinding
import id.application.pointofsales.utils.Utils.formatRupiah

class BouquetAdapter(
    private var items: List<BouquetItem>,
    private var onButtonClick: (BouquetItem) -> Unit,
) : RecyclerView.Adapter<BouquetAdapter.BouquetViewHolder>() {

    inner class BouquetViewHolder(val binding: ItemBouquetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BouquetItem) {
            with(binding){
                ivUrl.load(item.url)
                tvNameProduct.text = item.name
                tvTypeProduct.text = "Bouqet"
                tvHargaJual.text = formatRupiah(item.hargaJual)
                btnAddCart.setOnClickListener {
                    onButtonClick.invoke(item)
                }
            }
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

