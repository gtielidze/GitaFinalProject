package giorgi.tielidze.data

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import giorgi.tielidze.R
import giorgi.tielidze.TimesConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.Executors



@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
abstract class UserProfileDatabase : RoomDatabase() {
    abstract fun UserProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var instance: UserProfileDatabase? = null
        private const val TAG = "UserProfileDB"
        //const val DB_NAME = "UserProfile_DB"

        /**
         * Returns an instance of Room Database.
         *
         * @param context application context
         * @return The singleton SmileyDatabase
         */
        @Synchronized
        fun getInstance(context: Context): UserProfileDatabase {
            if (instance == null) {
                synchronized(UserProfileDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserProfileDatabase::class.java, context.getString(R.string.db_name)
                        )
                            .allowMainThreadQueries()
                            .addCallback(object : Callback() {
                                override fun onOpen(db: SupportSQLiteDatabase) {
                                    super.onOpen(db)
                                    Log.d(TAG, "onOpen: ")
                                }

                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Log.d(TAG, "onCreate: ")
                                    Executors.newSingleThreadScheduledExecutor()
                                        CoroutineScope(Dispatchers.IO).launch{ fillWithDemoData(context) }
                                }
                            }).build()
//                        val dao: UserProfileDao =
//                            getInstance(context).UserProfileDao()
//                        dao.getSmiley()
//                        Log.d(TAG, "getInstance: ")
                    }
                }
            }
            return instance!!
        }

        @WorkerThread
        private suspend fun fillWithDemoData(context: Context) {
            val dao: UserProfileDao = getInstance(context).UserProfileDao()
            val userProfile = loadJsonArray(context)
            try {
                for (i in 0 until userProfile!!.length()) {
                    val item = userProfile.getJSONObject(i)
                    val converter = TimesConverter()
                    dao.insert(
                        UserProfile(
                            item.getString("name"),
                            item.getString("blood group"),
                            item.getString("mobile number"),
                            item.getString("location"),
                            item.getString("last blood donated date"),
                            //converter.fromTimestamp(Integer.valueOf(item.getString("last blood donated date")).toLong()),
                            Integer.valueOf(item.getString("weight"))
                        )
                    )
                }
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
        }

        private fun loadJsonArray(context: Context): JSONArray? {
            val builder = StringBuilder()
            val `in` = context.resources.openRawResource(R.raw.blood)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("bloods")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }
    }
}
