package id.application.core.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.application.core.domain.model.admin_all_user.ItemAllUsers
import id.application.core.domain.repository.ApplicationRepository


class UsersPagingSource(
    private val repo : ApplicationRepository
): PagingSource<Int, ItemAllUsers>(){

    override fun getRefreshKey(state: PagingState<Int, ItemAllUsers>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemAllUsers> {
        val currentPage = params.key ?: 1
        return try {
            val response = repo.getAllUsers(
                pageItem = currentPage
            )

            if (response.success == true) {
                val usersResponse = response
                val items = usersResponse.data?.items ?: emptyList()
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (currentPage >= (usersResponse.data?.itemsPerPage ?: 0)) null else currentPage + 1
                LoadResult.Page(
                    data = items,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("Error code: ${response.message}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}