package com.example.testmc.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.testmc.data.API_KEY
import com.example.testmc.data.Resource
import com.example.testmc.data.datasource.MoviesRepository
import com.example.testmc.data.model.MoviesDto
import com.example.testmc.data.model.ResultDto
import com.example.testmc.view.PostersPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class PosterViewModel(val repository: MoviesRepository, application: Application) :
    AndroidViewModel(
        application
    ) {
    val breakingNews: MutableLiveData<Resource<MoviesDto>> = MutableLiveData()


    init {
       // getBreakingNews()
    }


    fun getBreakingNews() = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = repository.getPopularMovies(API_KEY,1)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun handleBreakingNewsResponse(respons: retrofit2.Response<MoviesDto>): Resource<MoviesDto> {
        if (respons.isSuccessful) {
            respons.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(respons.message())
    }

    fun getMoviesList(): Flow<PagingData<ResultDto>> {
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 200 ),
        pagingSourceFactory = {PostersPagingSource(repository)}).flow.cachedIn(viewModelScope)
    }

}