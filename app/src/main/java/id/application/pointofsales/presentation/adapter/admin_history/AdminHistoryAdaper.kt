package id.application.pointofsales.presentation.adapter.admin_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.application.pointofsales.databinding.ItemAdminHistoryBinding
import id.application.pointofsales.presentation.adapter.history.HistoryItem
import id.application.pointofsales.utils.Utils.formatRupiah

class AdminHistoryAdapter(
    private val historyList: List<HistoryItem>,
    private val onClickDetail: (HistoryItem) -> Unit
) : RecyclerView.Adapter<AdminHistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(private val binding: ItemAdminHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItem) {
            binding.ivUrl.load(item.imageUrl)

            binding.tvBouquetName.text = item.bouquetName
            binding.tvProduct.text = item.productType
            binding.tvDatetime.text = item.dateTime
            binding.tvHargaJual.text = formatRupiah(item.salePrice)

            binding.btnAddCart.setOnClickListener {
                onClickDetail(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemAdminHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

}

