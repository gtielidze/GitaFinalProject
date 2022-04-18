package giorgi.tielidze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import giorgi.tielidze.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            nextActivity()
        }
    }

    private fun nextActivity() {
        val intent = Intent(this, AvailableBloodActivity::class.java)
        startActivity(intent)
    }
}