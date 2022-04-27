package com.example.catalogue.ui.main

import androidx.lifecycle.*
import com.example.catalogue.entity.Movie
import com.example.catalogue.repository.MovieRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val movieRepository = MovieRepository()
    private val _movies = MutableLiveData(emptyList<Movie>())
    val movies: LiveData<List<Movie>> = _movies
    var job: Job? = null

    private fun findAll() = viewModelScope.launch {
        movieRepository.findAll().collect {
            _movies.value = it
        }
    }

    private fun find(searchText: String) = viewModelScope.launch {
        movieRepository.find(searchText).collect {
            _movies.value = it
        }
    }

    fun updateQuery(text: String) {
        job?.cancel()
        job = if(text.isBlank()){
            findAll()
        } else {
            find(text)
        }
    }
}