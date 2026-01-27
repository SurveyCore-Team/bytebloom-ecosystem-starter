package domain.usecase.performance
import domain.model.PerformanceSubmission
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class getPerformanceBreakdownForMenteeUseCase(
    private val menteeRepository: MenteeRepository
): BaseUseCase<String, List<PerformanceSubmission>> {
    override  fun invoke(menteeId: String): List<PerformanceSubmission> =
        menteeRepository.getAllMentees()
            .firstOrNull { it.id == menteeId }
            ?.submissions
            ?: emptyList()
}
