package com.example.catalogue.ui.main

import androidx.lifecycle.*
import com.example.catalogue.entity.Movie
import com.example.catalogue.repository.MovieRepository

class MainViewModel : ViewModel() {
    private val movieRepository = MovieRepository()
    private val queryText = MutableLiveData("")
    private val _movies = Transformations.switchMap(queryText){
        if(it.isNullOrBlank()){
            movieRepository.findAll().asLiveData()
        } else {
            movieRepository.find(it).asLiveData()
        }
    }
    val movies: LiveData<List<Movie>> = _movies

    fun updateQuery(text: String) {
        queryText.postValue(text)
    }
}