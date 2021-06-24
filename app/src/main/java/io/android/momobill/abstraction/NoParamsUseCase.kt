package io.android.momobill.abstraction

import io.android.momobill.vo.Either

abstract class NoParamsUseCase<T> {
    abstract suspend operator fun invoke(): Either<Exception, T>
}
