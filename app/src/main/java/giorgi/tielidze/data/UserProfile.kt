package giorgi.tielidze.data


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "blood_base")
@Keep
data class UserProfile(
    @SerializedName("name")
    var name: String,
    @SerializedName("blood group")
    var bloodGroup: String,
    @SerializedName("mobile number")
    var mobileNumber: String,
    @SerializedName("location")
    var location: String,
    @SerializedName("last blood donated date")
    var lastDonatedDate: String,
    @SerializedName("weight")
    var weight: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
