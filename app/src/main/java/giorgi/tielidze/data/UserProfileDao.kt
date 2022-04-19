package giorgi.tielidze.data

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userProfile: UserProfile)

    @Query("SELECT * FROM blood_base ORDER BY id ASC")
    suspend fun fetchAllUsersProfile():List<UserProfile>

    @Query("SELECT * FROM BLOOD_BASE ORDER BY id ASC")
    fun getAll(): PagingSource<Int, UserProfile>?

    @Query("SELECT * FROM BLOOD_BASE ORDER BY weight ASC")
    fun getAllByWeight(): PagingSource<Int, UserProfile>?

//    @Query("SELECT * FROM BLOOD_BASE ORDER BY lastDonatedDate ASC")
//    fun getAllByDate(): PagingSource<Int, UserProfile>

//    @Query("SELECT * FROM blood_base ORDER BY weight ASC")
//    suspend fun fetchAllUserProfileByWeight(): Flow<List<UserProfile>>

//    @Query("SELECT * FROM blood_base ORDER BY lastDonatedDate ASC")
//    suspend fun fetchAllUserProfileByDate():Flow<List<UserProfile>>
}