package io.android.momobill.domain.repository

import io.android.momobill.domain.model.Movie
import io.android.momobill.vo.LoadResult

interface UserRepository {
    suspend fun getMovies(): LoadResult<List<Movie>>
}