package domain.usecase.performance

import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class getMenteePerformanceSummaryUseCase(
    private val menteeRepository: MenteeRepository
): BaseUseCase<String, Triple<Double, Double, Int>> {
    override fun invoke(menteeId: String): Triple<Double, Double, Int> {
        return menteeRepository.getAllMentees()
            .first { it.id == menteeId }
            .performanceSummary()
    }

    private fun Mentee.performanceSummary(): Triple<Double, Double, Int> {
        val total = submissions.sumOf { it.score }
        val count = submissions.size
        val average = if (count == 0) 0.0 else total / count

        return Triple(total, average, count)
    }
}