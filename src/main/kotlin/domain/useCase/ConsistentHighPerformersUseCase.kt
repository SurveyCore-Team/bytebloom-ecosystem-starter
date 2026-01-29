package domain.useCase
import domain.usecase.BaseUseCase
import domain.model.Mentee

class ConsistentHighPerformersUseCase (
    private val minimumScore: Double = 80.0
) : BaseUseCase<List<Mentee>, List<Mentee>> {
    override fun invoke(input: List<Mentee>): List<Mentee> {
        return input.filter { mentee ->
            mentee.submissions.isNotEmpty() &&
                    mentee.submissions.all { it.score >= minimumScore }
        }
    }
}