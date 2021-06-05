package io.android.momobill.data.mapper

import io.android.momobill.abstraction.Mapper
import io.android.momobill.data.model.MoviesResponse
import io.android.momobill.domain.model.Movie

class MoviesResponseMapper : Mapper<MoviesResponse, List<Movie>>() {
    override operator fun invoke(dto: MoviesResponse): List<Movie> {
        return dto.results?.map { result ->
            Movie(
                adult = result?.adult ?: false,
                backdropPath = result?.backdropPath.orEmpty(),
                genreIds = result?.genreIds?.map { it ?: 0 } ?: emptyList(),
                id = result?.id ?: 0,
                originalLanguage = result?.originalLanguage.orEmpty(),
                originalTitle = result?.originalTitle.orEmpty(),
                overview = result?.overview.orEmpty(),
                popularity = result?.popularity ?: 0f,
                posterPath = result?.posterPath.orEmpty(),
                releaseDate = result?.releaseDate.orEmpty(),
                title = result?.title.orEmpty(),
                video = result?.video ?: false,
                voteAverage = result?.voteAverage ?: 0f,
                voteCount = result?.voteCount ?: 0
            )
        } ?: emptyList()
    }
}
