package domain.services

import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.repository.contracts.MenteeRepository

class MenteeService(
    private val menteeRepository: MenteeRepository,
) {
    fun findTopScoringMenteeOverall(): Mentee? {
        val mentees = menteeRepository.getAllMentees()
        return mentees.maxByOrNull { mentee ->
            mentee.submissions
                .map { it.score.toDouble() }
                .average()
                .takeUnless { it.isNaN() } ?: 0.0
        }
    }

    fun getPerformanceBreakdownForMentee(menteeId: String): List<PerformanceSubmission> {
        val mentee = menteeRepository.getAllMentees().find { it.id == menteeId }
        return mentee?.submissions ?: emptyList()


    }
}