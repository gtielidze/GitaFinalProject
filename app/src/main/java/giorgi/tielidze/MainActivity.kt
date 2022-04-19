package giorgi.tielidze

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import giorgi.tielidze.data.UserProfile
import giorgi.tielidze.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()

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
        val userProfile = UserProfile(
            name = binding.txtName.text.toString(),
            bloodGroup = binding.txtBloodGroup.text.toString(),
            mobileNumber = binding.txtMobileNumber.text.toString(),
            location = binding.txtLocation.text.toString(),
            lastDonatedDate = binding.txtLastDonatedDate.text.toString(),
            weight = Integer.valueOf(binding.txtWeight.text.toString())
        )
        viewModel.insert(userProfile)
    }
}