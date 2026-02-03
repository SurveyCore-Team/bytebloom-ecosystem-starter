package domain.usecase

import domain.model.Mentee
import domain.model.attendance.AttendanceStatus
import domain.repository.MenteeRepository

class FindMenteesWithPerfectAttendanceUseCase(
    private val menteeRepository: MenteeRepository

) : BaseUseCase<Unit, List<Mentee>> {
    override fun invoke(input: Unit): List<Mentee> {
        return menteeRepository.getAllMentees().filter {
            it.hasPerfectAttendance()
        }
    }

    private fun Mentee.hasPerfectAttendance(): Boolean =
        this.attendanceRecords.all { it.status == AttendanceStatus.PRESENT }
}