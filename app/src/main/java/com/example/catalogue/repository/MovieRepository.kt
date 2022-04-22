package com.example.catalogue.repository

import com.example.catalogue.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository {
    fun findAll(): Flow<List<Movie>>{
        //TODO
        return flow {  }
    }

    fun find(searchText: String): Flow<List<Movie>>{
        //TODO
        return flow {  }
    }
}