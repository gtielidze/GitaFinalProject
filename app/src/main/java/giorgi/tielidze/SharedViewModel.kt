package giorgi.tielidze

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import giorgi.tielidze.data.UserProfile
import giorgi.tielidze.data.UserProfileDao
import giorgi.tielidze.data.UserProfileDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private var dao: UserProfileDao =
        UserProfileDatabase.getInstance(app.applicationContext).UserProfileDao()


    val items: Flow<PagingData<UserProfile>> = Pager(PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
        dao.getAll()!!
    }.flow

    val itemsInLiveData:LiveData<PagingData<UserProfile>> = Pager(PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
        dao.getAllByWeight()!!
    }.liveData


//    val itemsInLiveDataByDate:LiveData<PagingData<UserProfile>> = Pager(PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
//        dao.getAllByDate()!!
//    }.liveData




    fun insert(userProfile: UserProfile) {
        viewModelScope.launch {
            dao.insert(userProfile)
        }
    }


}
