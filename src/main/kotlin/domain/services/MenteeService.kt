package domain.services

import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.repository.contracts.MenteeRepository
import domain.repository.contracts.PerformanceSubmissionRepository

class MenteeService(
    private val menteeRepository: MenteeRepository,
    private val performanceRepository: PerformanceSubmissionRepository
) {
    fun findTopScoringMenteeOverall(): Mentee? {
        val allSubmissions = performanceRepository.getAllPerformanceSubmission()
        val topSubmission = allSubmissions.maxByOrNull { it.score }
        return topSubmission?.let { submission ->
            menteeRepository.getAllMentees().find { it.id == submission.menteeId }
        }
    }
    fun getPerformanceBreakdownForMentee(menteeId: String): List<PerformanceSubmission> {
        return performanceRepository.getAllPerformanceSubmission()
            .filter { it.menteeId == menteeId }
    }
}