package io.android.momobill.data.source

import io.android.momobill.BuildConfig
import io.android.momobill.data.model.MoviesResponse
import io.android.momobill.data.service.ApiService
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.CoroutineDispatcher

class UserRemoteDataSource(private val service: ApiService) : RemoteDataSource() {

    suspend fun getMovies(dispatcher: CoroutineDispatcher): LoadResult<MoviesResponse> {
        return call(dispatcher) { service.getMovies(BuildConfig.API_KEY) }
    }
}