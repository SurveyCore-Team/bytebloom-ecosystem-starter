package domain.services

import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.repository.contracts.MenteeRepository

class MenteeService(
    private val menteeRepository: MenteeRepository
) {
    private fun Mentee.hasPerfectAttendance(): Boolean {
        return this.attendanceRecords.all { it.status.lowercase() == "present" }
    }

    fun findMenteesWithPerfectAttendance(): List<Mentee> {
        return menteeRepository.getAllMentees().filter {
            it.hasPerfectAttendance()
        }
    }

    private fun Mentee.hasPoorAttendance(minAbsences: Int): Boolean {
        return attendanceRecords.count { it.status.lowercase() == "absent" } > minAbsences
    }

    fun findMenteesWithPoorAttendance(minAbsences: Int): List<Mentee> {
        return menteeRepository.getAllMentees().filter {
            it.hasPoorAttendance(minAbsences)
        }
    }

    fun findTopScoringMenteeOverall(): Mentee? {
        val mentees = menteeRepository.getAllMentees()
        if (mentees.isEmpty()) return null
        return mentees.maxByOrNull { mentee ->
            val currentSubmissions = mentee.submissions ?: emptyList()
            val scores = currentSubmissions.mapNotNull {
                it.score.toString().toDoubleOrNull()
            }
            if (scores.isEmpty()) {
                0.0
            } else {
                scores.average()
            }
        }
    }

    fun getPerformanceBreakdownForMentee(menteeId: String): List<PerformanceSubmission> {
        val mentee = menteeRepository.getAllMentees().find { it.id == menteeId }
        return mentee?.submissions ?: emptyList()
    }
}