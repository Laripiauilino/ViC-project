package com.larissa.integrativeproject.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import com.larissa.integrativeproject.R
import com.larissa.integrativeproject.databinding.ActivityHomeBinding
import com.larissa.integrativeproject.presentation.adapter.SectionsPagerAdapter


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var searchMoviesFragment: SearchMoviesFragment? = null

    companion object {
        private const val TAB_1 = "Todos os Filmes"
        private const val TAB_2 = "Favoritos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        viewPager.adapter = SectionsPagerAdapter(this)
        viewPager.isUserInputEnabled = false


        val tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> TAB_1
                1-> TAB_2
                else -> null
            }
        }.attach()


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val receivedQuery = binding.searchView.query.toString()
                setupSearchFragment(receivedQuery)

                return true
            }

            override fun onQueryTextChange(newQuery: String): Boolean {
                val newQuery: String = binding.searchView.query.toString()
                if (newQuery.isEmpty()) {
                    backToHomeActivity()
                    onDestroy()
                } else {
                    searchMoviesFragment?.updateSearch(newQuery)

                }
                return false
            }
        })

        binding.btnBack.setOnClickListener {
            backToHomeActivity()
            binding.searchView.setQuery("",false)
        }
    }

    private fun setupSearchFragment(receivedQuery: String) {
        binding.tabLayout.visibility = View.GONE
        binding.viewPager.visibility = View.GONE
        binding.groupSearch.visibility = View.VISIBLE
        binding.frmSearchContainer.visibility = View.VISIBLE
        if (searchMoviesFragment == null){
            searchMoviesFragment = SearchMoviesFragment.newInstance(receivedQuery)
            searchMoviesFragment?.let {
                supportFragmentManager.beginTransaction()
                    .add(R.id.frmSearchContainer, it)
                    .addToBackStack(null)
                    .commit()
            }
        }else{
            searchMoviesFragment?.updateSearch(receivedQuery)
            }
    }

    private fun backToHomeActivity(){
        binding.tabLayout.visibility = View.VISIBLE
        binding.viewPager.visibility = View.VISIBLE
        binding.groupSearch.visibility = View.INVISIBLE
        binding.frmSearchContainer.visibility = View.INVISIBLE
    }
}



