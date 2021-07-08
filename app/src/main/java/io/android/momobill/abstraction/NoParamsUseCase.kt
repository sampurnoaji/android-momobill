package io.android.momobill.abstraction

import io.android.momobill.vo.Either

interface NoParamsUseCase<T> {
    suspend operator fun invoke(): Either<Exception, T>
}
