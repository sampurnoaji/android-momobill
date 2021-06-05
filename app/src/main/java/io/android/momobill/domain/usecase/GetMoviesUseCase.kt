package io.android.momobill.domain.usecase

import io.android.momobill.abstraction.UseCase
import io.android.momobill.domain.model.Movie
import io.android.momobill.domain.repository.UserRepository
import io.android.momobill.vo.LoadResult

class GetMoviesUseCase(private val repository: UserRepository): UseCase<UseCase.None, LoadResult<List<Movie>>>() {

    override suspend fun invoke(params: None): LoadResult<List<Movie>> {
        return repository.getMovies()
    }
}