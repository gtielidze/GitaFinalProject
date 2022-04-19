package giorgi.tielidze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import giorgi.tielidze.data.UserProfile
import giorgi.tielidze.data.UserProfileDao
import giorgi.tielidze.data.UserProfileDatabase
import giorgi.tielidze.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            addInDatabase()
            nextActivity()
        }
    }

    private fun nextActivity() {
        val intent = Intent(this, AvailableBloodActivity::class.java)
        startActivity(intent)
    }

    private fun addInDatabase() {
        val dao: UserProfileDao = UserProfileDatabase.getInstance(this).UserProfileDao()
        val userProfile = UserProfile(
            name = binding.txtName.text.toString(),
            bloodGroup = binding.txtBloodGroup.text.toString(),
            mobileNumber = binding.txtMobileNumber.toString(),
            location = binding.txtLocation.text.toString(),
            lastDonatedDate = binding.txtLastDonatedDate.toString(),
            weight = binding.txtWeight.text.toString()
        )
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(userProfile)
        }

    }
}