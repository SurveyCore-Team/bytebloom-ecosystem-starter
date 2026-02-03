package domain.usecase

import domain.model.Mentee
import domain.model.attendance.AttendanceStatus
import domain.repository.MenteeRepository

class FindMenteesWithPoorAttendanceUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Int, List<Mentee>> {
    override fun invoke(minAbsences: Int): List<Mentee> {
        return menteeRepository.getAllMentees().filter {
            it.hasPoorAttendance(minAbsences)
        }
    }

    private fun Mentee.hasPoorAttendance(minAbsences: Int): Boolean {
        return attendanceRecords.count {
            it.status in listOf(
                AttendanceStatus.LATE,
                AttendanceStatus.PRESENT
            )
        } > minAbsences
    }
}