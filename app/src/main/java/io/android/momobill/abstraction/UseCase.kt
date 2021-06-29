package io.android.momobill.abstraction

import io.android.momobill.vo.Either

abstract class UseCase<Params, T> {
    abstract suspend operator fun invoke(params: Params): Either<Exception, T>

    object None
}
