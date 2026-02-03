package domain.usecase

import domain.model.Mentee
import domain.repository.MenteeRepository

class ConsistentHighPerformanceUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Double, List<Mentee>> {

    override fun invoke(input: Double): List<Mentee> {
        return menteeRepository
            .getAllMentees()
            .filter { it.isConsistentHighPerformer(input) }
    }

    private fun Mentee.isConsistentHighPerformer(minScore: Double): Boolean {
        return hasSubmissions() && hasAllScoresAbove(minScore)
    }

    private fun Mentee.hasSubmissions(): Boolean {
        return submissions.isNotEmpty()
    }

    private fun Mentee.hasAllScoresAbove(minScore: Double): Boolean {
        return submissions.all { it.score >= minScore }
    }
}