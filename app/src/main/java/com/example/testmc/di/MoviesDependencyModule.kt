package com.example.testmc.di

import com.example.testmc.data.datasource.MoviesRepository
import com.example.testmc.view.PostersPagingSource
import com.example.testmc.view.fragment.PostersListFragment
import com.example.testmc.vm.PosterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel(override = true) {
        PosterViewModel(repository = get(), application = get())
    }
}

val repositoryModule = module {
    single { MoviesRepository() }
}

val pagingModule = module {
    single { PostersPagingSource(moviesRepository = get()) }
}