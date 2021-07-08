package io.android.momobill.abstraction

interface Mapper<Dto, Domain> {
    operator fun invoke(dto: Dto): Domain
}
