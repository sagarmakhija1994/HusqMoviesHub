package com.sagarmakhija1994.husqmovieshub.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sagarmakhija1994.husqmovieshub.view.movieList.MoviesListViewModel

class MyViewModelFactory constructor(private val repository: MainNetworkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesListViewModel::class.java) -> {
                MoviesListViewModel(this.repository) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}