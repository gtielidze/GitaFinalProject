package giorgi.tielidze

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import giorgi.tielidze.data.UserProfileDao
import giorgi.tielidze.data.UserProfileDatabase

class AvailLiableBloodViewModel(app: Application) : AndroidViewModel(app) {

    private var dao: UserProfileDao =
        UserProfileDatabase.getInstance(app.applicationContext).UserProfileDao()

    val items = Pager(PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
        dao.getAll()!!
    }.flow

}
