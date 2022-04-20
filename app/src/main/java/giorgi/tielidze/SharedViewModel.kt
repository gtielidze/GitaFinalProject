package giorgi.tielidze

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import giorgi.tielidze.data.UserProfile
import giorgi.tielidze.data.UserProfileDao
import giorgi.tielidze.data.UserProfileDatabase
import kotlinx.coroutines.launch

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private var dao: UserProfileDao =
        UserProfileDatabase.getInstance(app.applicationContext).UserProfileDao()


//    val items: Flow<PagingData<UserProfile>> =
//        Pager(PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
//            dao.getAll()!!
//        }.flow
//            .cachedIn(viewModelScope)


    fun getPager(query: String) =
        Pager(PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
            when (query) {
                "by weight" -> dao.getAllByWeight()!!
                "by date" -> dao.getAllByDate()!!
                else -> {
                    dao.getAllByDate()!!
                }
            }
        }.liveData.cachedIn(viewModelScope)

    fun insert(userProfile: UserProfile) {
        viewModelScope.launch {
            dao.insert(userProfile)
        }
    }

}
