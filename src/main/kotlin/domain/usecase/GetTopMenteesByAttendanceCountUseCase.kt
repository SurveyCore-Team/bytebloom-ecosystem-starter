package domain.usecase

import domain.model.Mentee
import domain.model.attendance.AttendanceStatus
import domain.repository.MenteeRepository

class GetTopMenteesByAttendanceCountUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Int, List<Mentee>> {

    override fun invoke(numberOfTopMentees: Int): List<Mentee> {
        return menteeRepository.getAllMentees()
            .sortedByDescending { it.presentCount() }
            .take(numberOfTopMentees)
    }

    private fun Mentee.presentCount(): Int {
        return attendanceRecords.count { it.status == AttendanceStatus.PRESENT }
    }
}