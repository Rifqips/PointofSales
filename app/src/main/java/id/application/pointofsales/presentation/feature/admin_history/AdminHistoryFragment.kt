package id.application.pointofsales.presentation.feature.admin_history

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import id.application.pointofsales.databinding.FragmentAdminHistoryBinding
import id.application.pointofsales.presentation.adapter.admin_history.AdminHistoryAdapter
import id.application.pointofsales.presentation.adapter.history.HistoryItem
import id.application.pointofsales.presentation.viewmodel.VmApplication
import id.application.pointofsales.utils.BaseFragment


class AdminHistoryFragment :
    BaseFragment<FragmentAdminHistoryBinding, VmApplication>(FragmentAdminHistoryBinding::inflate){

    override val viewModel: VmApplication by viewModels()

    private lateinit var adapter : AdminHistoryAdapter

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

        adapter = AdminHistoryAdapter(historyList) { item ->
        }
        binding.rvAdminUsers.adapter = adapter
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvAdminUsers.layoutManager = gridLayoutManager
    }


}