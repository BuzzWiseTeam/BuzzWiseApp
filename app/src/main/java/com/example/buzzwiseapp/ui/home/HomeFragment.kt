package com.example.buzzwiseapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzzwiseapp.R
import com.example.buzzwiseapp.data.JobAdapter
import com.example.buzzwiseapp.data.ViewModelFactory
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.databinding.FragmentHomeBinding
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel by viewModels<HomeViewModel> {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        binding.root.setOnRefreshListener {
            setupView()
        }
        setupView()
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupView()
        setupViewModel()
        //return view
        return binding.root
    }

    private fun setupView() {
        binding.root.isRefreshing = false
        mainViewModel.getJob()
    }

    private fun setupViewModel() {
        mainViewModel.listJob.observe(requireActivity()) {
            setRecycleView(it)
        }
        mainViewModel.loadingScreen.observe(requireActivity()) {
            showLoading(it)
        }
    }
    private fun showLoading(value: Boolean) {
        binding.pbLoadingScreen.isVisible = value
        binding.rvListMain.isVisible = !value
    }
    private fun setRecycleView(list: List<DataItem>) {
        with(binding) {
            val manager = LinearLayoutManager(requireActivity())
            rvListMain.apply {
                adapter = JobAdapter(list)
                layoutManager = manager
            }
        }
    }

}