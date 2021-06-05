package io.android.momobill.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.momobill.abstraction.UseCase
import io.android.momobill.domain.model.Movie
import io.android.momobill.domain.usecase.GetMoviesUseCase
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.launch

class LoginViewModel(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    private val _movies = MutableLiveData<LoadResult<List<Movie>>>()
    val movies = liveData { emitSource(_movies) }

    fun getMovies() {
        _movies.value = LoadResult.Loading
        viewModelScope.launch {
            _movies.value = getMoviesUseCase(UseCase.None)
        }
    }
}