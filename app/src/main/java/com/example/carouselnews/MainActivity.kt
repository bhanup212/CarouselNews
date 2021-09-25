package com.example.carouselnews

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carouselnews.databinding.ActivityMainBinding
import com.example.carouselnews.di.AppComponentInitializer
import com.example.carouselnews.ui.adapter.NewsAdapter
import com.example.carouselnews.ui.viewmodel.NewsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by viewModels { viewModelFactory }

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppComponentInitializer.getComponent().inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpMatchesRv()
        viewModel.getNewsData()
        observeViewModel()
    }

    private fun setUpMatchesRv() {
        adapter = NewsAdapter(this)
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun observeViewModel() {
        viewModel.errorMessage.observe(
            this,
            { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        )
        viewModel.isLoading.observe(
            this,
            Observer { isLoading ->
                binding.progressbar.isVisible = isLoading
            }
        )
        viewModel.newsList.observe(
            this,
            {
                if (it != null) adapter.updateNews(it)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.recent){
            viewModel.sortByDate()
            return true
        }else if (item.itemId == R.id.popular){
            viewModel.sortByPopular()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
