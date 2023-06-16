package com.example.buzzwiseapp.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buzzwiseapp.R
import com.example.buzzwiseapp.data.JobAdapter
import com.example.buzzwiseapp.data.response.DataItem
import com.example.buzzwiseapp.data.response.SimilarWordsItem
import com.example.buzzwiseapp.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {
    private lateinit var binding : FragmentExploreBinding
    private lateinit var exploreViewModel: ExploreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    //This fragment not yet to be implemented

    /*private fun setupView() {
        exploreViewModel.getSearchUser()
    }

    private fun setupViewModel() {
        exploreViewModel.listData.observe(requireActivity()) {
            setRecycleView(it)
        }
    }

    private fun setRecycleView(list: List<SimilarWordsItem>) {
        with(binding) {
            val manager = LinearLayoutManager(requireActivity())
            rvListMain.apply {
                adapter = JobAdapter(SimilarWordsItem)
                layoutManager = manager
            }
        }
    }*/
}