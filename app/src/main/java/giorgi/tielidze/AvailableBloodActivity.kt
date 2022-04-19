package giorgi.tielidze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import giorgi.tielidze.adapter.UserProfileAdapter
import giorgi.tielidze.databinding.ActivityAvalalbleBloodBinding
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AvailableBloodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAvalalbleBloodBinding
    private val viewModel: AvailLiableBloodViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvalalbleBloodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userProfileAdapter = UserProfileAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AvailableBloodActivity)
            setHasFixedSize(true)
            adapter = userProfileAdapter
        }

        lifecycleScope.launch {
            viewModel.items.collectLatest {
                userProfileAdapter.submitData(it)
            }
        }
    }
}