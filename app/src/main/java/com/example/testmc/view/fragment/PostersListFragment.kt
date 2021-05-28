package com.example.testmc.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmc.R
import com.example.testmc.data.Resource
import com.example.testmc.databinding.FragmentPosterListBinding
import com.example.testmc.view.adapter.MoviesAdapter
import com.example.testmc.vm.PosterViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

private const val TAG = "PostersListFragment"
class PostersListFragment : Fragment(R.layout.fragment_poster_list) {

    private var _binding: FragmentPosterListBinding? = null

    private val binding: FragmentPosterListBinding
    get() = _binding ?: throw IllegalArgumentException("snc")

    lateinit var recyclerAdapter: MoviesAdapter

    private val vm: PosterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPosterListBinding.bind(view)
        binding.lifecycleOwner = this
        initRecycler()
        setupObserver()
    }

    fun initRecycler() {
        binding.rvPoster.apply {
            val manager = GridLayoutManager(requireContext(),3)
            //manager.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = manager
            recyclerAdapter = MoviesAdapter()
            adapter = recyclerAdapter
        }


        lifecycleScope.launchWhenCreated {
            vm.getMoviesList().collectLatest {
                recyclerAdapter.submitData(it)
            }
        }
    }

    
    fun setupObserver(){
        vm.breakingNews.observe(viewLifecycleOwner){ resource ->
            when(resource){
                is Resource.Success -> {
                    Log.d(TAG, "setupObserver: success "+resource.data)
                }
                is Resource.Error -> {
                    Log.d(TAG, "setupObserver: Error"+resource.message)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "setupObserver: Loading")
                }
            }
            
        }
    }

}