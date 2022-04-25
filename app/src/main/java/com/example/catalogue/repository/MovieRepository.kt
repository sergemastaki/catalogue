package com.example.catalogue.repository

import android.util.Log
import com.example.catalogue.entity.Movie
import com.example.catalogue.network.ApiHelper
import com.example.catalogue.network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.URLEncoder

class MovieRepository {
    private val movieApiService = ApiHelper.client.create(MovieApiService::class.java)
    suspend fun findAll(): Flow<List<Movie>> = withContext(Dispatchers.IO) {
        try {
            val moviePageDto = movieApiService.findAll(
                page = 1 /*, adult = false, language = "fr-FR"*/
            )
            return@withContext flow {
                emit(moviePageDto.movieList.map {
                    it.toEntity()
                })
            }
        } catch (exception: RuntimeException) {
            Log.e("MOVIE_REPOSITORY", exception.message, exception)
            return@withContext emptyFlow()
        }
    }

    suspend fun find(searchText: String): Flow<List<Movie>> = withContext(Dispatchers.IO) {
        val queryText = URLEncoder.encode(searchText, "utf-8")
        try {
            val moviePageDto = movieApiService.find(
                searchText = queryText,
                page = 1 /*, adult = false, language = "fr-FR"*/
            )
            return@withContext flow {
                emit(moviePageDto.movieList.map {
                    it.toEntity()
                })
            }
        } catch (exception: RuntimeException) {
            Log.e("MOVIE_REPOSITORY", exception.message, exception)
            return@withContext emptyFlow()
        }
    }
}