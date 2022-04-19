package giorgi.tielidze.adapter

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import giorgi.tielidze.data.UserProfile
import giorgi.tielidze.data.UserProfileDatabase

//class UserProfilePagingSource(private val repository: UserProfileRepository) :
//    PagingSource<Int, UserProfile>() {
//    override fun getRefreshKey(state: PagingState<Int, UserProfile>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserProfile> {
//        val currentLoadingPageKey = params.key ?: 1
//        val list = mutableListOf<UserProfile>()
//
//        val listFromDB: List<UserProfile> = repository.getList()
//        list.addAll(listFromDB)
//
//        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
//        return LoadResult.Page(
//            data = listFromDB,
//            prevKey = prevKey,
//            nextKey = if (listFromDB.isEmpty()) null else currentLoadingPageKey + 1
//        )
//
//    }
//}

@OptIn(ExperimentalPagingApi::class)
class UserProfileMediator(val usersDatabase: UserProfileDatabase): RemoteMediator<Int, UserProfile>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserProfile>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}