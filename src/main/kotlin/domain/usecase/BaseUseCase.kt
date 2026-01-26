package domain.useCase

interface BaseUseCase<Input , Output> {
    operator fun invoke(input: Input): Output?
}