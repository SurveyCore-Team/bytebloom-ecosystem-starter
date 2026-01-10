package domain.services

import domain.model.Mentee
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

}