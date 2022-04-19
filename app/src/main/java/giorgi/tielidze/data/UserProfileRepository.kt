package giorgi.tielidze.data

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserProfileRepository(private val userProfileDao: UserProfileDao) {

//    suspend fun getList(): List<UserProfile> {
//        val list = mutableListOf<UserProfile>()
//        CoroutineScope(Dispatchers.IO).launch {
//            list.addAll(userProfileDao.fetchAllUsersProfile())
//        }
//        return list.toList()
//    }

}
