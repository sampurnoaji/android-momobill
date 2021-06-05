package io.android.momobill.data.source

import io.android.momobill.data.dispatcher.DispatcherProvider
import io.android.momobill.data.mapper.MoviesResponseMapper
import io.android.momobill.domain.model.Movie
import io.android.momobill.domain.repository.UserRepository
import io.android.momobill.extension.mapApiResultToDomain
import io.android.momobill.vo.LoadResult

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val dispatcher: DispatcherProvider,
    private val moviesResponseMapper: MoviesResponseMapper
) : UserRepository {

    override suspend fun getMovies(): LoadResult<List<Movie>> {
        val apiResult = remoteDataSource.getMovies(dispatcher.io)
        return apiResult.mapApiResultToDomain(moviesResponseMapper)
    }
}