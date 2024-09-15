package id.application.pointofsales.presentation.feature.history

import androidx.recyclerview.widget.GridLayoutManager
import id.application.pointofsales.databinding.FragmentHistoryBinding
import id.application.pointofsales.presentation.adapter.history.HistoryAdapter
import id.application.pointofsales.presentation.adapter.history.HistoryItem
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, VmApplication>(FragmentHistoryBinding::inflate) {

    override val viewModel: VmApplication by viewModel()
    private lateinit var historyAdapter: HistoryAdapter

    override fun initView() {
        setupRecyclerView()
    }

    override fun initListener() {}

    private fun setupRecyclerView() {
        val historyList = listOf(
            HistoryItem("https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg", "Bouquet Rose", "Bouquet", "10-10-2024", 100000),
            HistoryItem("https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg", "Bouquet Tulip", "Bouquet", "11-10-2024", 150000),
            HistoryItem("https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg", "Bouquet Rose", "Bouquet", "10-10-2024", 100000),
            HistoryItem("https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg", "Bouquet Tulip", "Bouquet", "11-10-2024", 150000),
            HistoryItem("https://i.pinimg.com/originals/54/a8/48/54a848594dbfe7aa3c37881d928c352c.jpg", "Bouquet Rose", "Bouquet", "10-10-2024", 100000),
            HistoryItem("https://i.pinimg.com/736x/98/84/94/9884942884bc2368767f87799b7526e8.jpg", "Bouquet Tulip", "Bouquet", "11-10-2024", 150000),
        )

        historyAdapter = HistoryAdapter(historyList) { item ->
        }
        binding.rvHistory.adapter = historyAdapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvHistory.layoutManager = gridLayoutManager
    }

}