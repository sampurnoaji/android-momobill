package io.android.momobill.abstraction

abstract class Mapper<Dto, Domain> {
    abstract operator fun invoke(dto: Dto): Domain
}
