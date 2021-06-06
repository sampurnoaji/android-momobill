package io.android.momobill.util

import io.android.momobill.abstraction.Mapper
import io.android.momobill.data.dto.BaseResponse
import io.android.momobill.vo.LoadResult

fun <I, O> LoadResult<I>.mapApiResultToDomain(mapper: Mapper<I, O>): LoadResult<O> {
    return when (this) {
        is LoadResult.Success -> LoadResult.Success(mapper(this.data))
        is LoadResult.Error -> LoadResult.Error(this.cause, this.code, this.errorMessage, this.status)
        else -> LoadResult.Error()
    }
}

fun LoadResult<BaseResponse>.mapApiResultToSuccessOrFailure(): LoadResult<Boolean> {
    return when (this) {
        is LoadResult.Success -> LoadResult.Success(this.data.success ?: false)
        is LoadResult.Error -> LoadResult.Error(this.cause, this.code, this.errorMessage, this.status)
        else -> LoadResult.Error()
    }
}