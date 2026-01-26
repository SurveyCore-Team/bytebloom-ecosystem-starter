package domain.usecase

interface BaseUseCase<Input , Output> {
    operator fun invoke(input: Input): Output
}