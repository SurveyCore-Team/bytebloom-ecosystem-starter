package domain.useCase

import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase
import domain.model.Mentee
import domain.model.attendance.AttendanceStatus

class AtRiskMenteesUseCase(private val menteeRepository: MenteeRepository) :
    BaseUseCase<Pair<Int, Double>, List<Mentee>?> {
    override fun invoke(input: Pair<Int, Double>): List<Mentee>? {
        val (absencesLimit, scoreLimit) = input
        return menteeRepository.getAllMentees().filter { it.isAtRisk(absencesLimit, scoreLimit) }
    }

    private fun Mentee.isAtRisk(absencesLimit: Int, scoreLimit: Double): Boolean {
        return hasExceededAbsenceLimit(absencesLimit) || hasScoreBelowMinimum(scoreLimit)
    }

    private fun Mentee.hasExceededAbsenceLimit(absencesLimit: Int): Boolean {
        val count = attendanceRecords.count { it.status != AttendanceStatus.PRESENT }
        return count >= absencesLimit
    }

    private fun Mentee.hasScoreBelowMinimum(scoreLimit: Double): Boolean {
        if (submissions.isEmpty()) return true
        return submissions.map { it.score }.average() < scoreLimit
    }
}