package giorgi.tielidze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import giorgi.tielidze.adapter.UserProfileAdapter
import giorgi.tielidze.databinding.ActivityAvalalbleBloodBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AvailableBloodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAvalalbleBloodBinding
    private val viewModel: SharedViewModel by viewModels()
    private val userProfileAdapter = UserProfileAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvalalbleBloodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AvailableBloodActivity)
            setHasFixedSize(true)
            adapter = userProfileAdapter
        }
        initialData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sortByDate -> {
                //sortByDate()
                true
            }
            R.id.sortByWeight -> {
                sortByWeight()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //data in flow
    private fun initialData() {
        lifecycleScope.launch {
            viewModel.items.collectLatest {
                userProfileAdapter.submitData(it)
            }
        }
    }
    //data in livedata
    private fun sortByWeight() {
        viewModel.itemsInLiveData.observe(this) {
            userProfileAdapter.submitData(lifecycle, it)
        }
    }

    fun sortByDate() {



    }


}