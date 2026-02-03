package domain.usecase

import domain.model.Mentee
import domain.repository.MenteeRepository

class PerformanceConsistencyUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Double, Map<String, PerformanceConsistency>> {

    override fun invoke(input: Double): Map<String, PerformanceConsistency> {
        return menteeRepository
            .getAllMentees()
            .associate { mentee ->
                mentee.id to mentee.evaluatePerformanceConsistency(input)
            }
    }

    private fun Mentee.evaluatePerformanceConsistency(
        varianceThreshold: Double
    ): PerformanceConsistency {
        val scores = extractScores()

        if (scores.size < 2) {
            return PerformanceConsistency.CONSISTENT
        }

        val average = calculateAverage(scores)
        val variance = calculateVariance(scores, average)

        return if (variance <= varianceThreshold) {
            PerformanceConsistency.CONSISTENT
        } else {
            PerformanceConsistency.INCONSISTENT
        }
    }

    private fun Mentee.extractScores(): List<Double> {
        return submissions.map { it.score }
    }

    private fun calculateAverage(scores: List<Double>): Double {
        return scores.average()
    }

    private fun calculateVariance(
        scores: List<Double>,
        average: Double
    ): Double {
        return scores
            .map { (it - average) * (it - average) }
            .average()
    }
}
enum class PerformanceConsistency {
    CONSISTENT,
    INCONSISTENT
}