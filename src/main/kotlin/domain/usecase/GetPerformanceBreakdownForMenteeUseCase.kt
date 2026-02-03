package domain.usecase
import domain.model.PerformanceSubmission
import domain.repository.MenteeRepository

class GetPerformanceBreakdownForMenteeUseCase(
    private val menteeRepository: MenteeRepository
): BaseUseCase<String, List<PerformanceSubmission>> {
    override  fun invoke(menteeId: String): List<PerformanceSubmission> =
        menteeRepository.getAllMentees()
            .firstOrNull { it.id == menteeId }
            ?.submissions
            ?: emptyList()
 }
