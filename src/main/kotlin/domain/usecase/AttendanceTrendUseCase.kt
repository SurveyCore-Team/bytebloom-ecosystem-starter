package domain.usecase

import domain.model.Mentee
import domain.model.attendance.AttendanceStatus
import domain.repository.MenteeRepository

class AttendanceTrendUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Unit, Map<String, AttendanceTrend>> {

    override fun invoke(input: Unit): Map<String, AttendanceTrend> {
        return menteeRepository
            .getAllMentees()
            .associate { mentee ->
                mentee.id to mentee.calculateAttendanceTrend()
            }
    }

    private fun Mentee.calculateAttendanceTrend(): AttendanceTrend {
        val statuses = extractAttendanceStatuses()

        return if (isDecliningAttendance(statuses)) {
            AttendanceTrend.DECLINING
        } else {
            AttendanceTrend.STABLE_OR_IMPROVING
        }
    }

    private fun Mentee.extractAttendanceStatuses(): List<AttendanceStatus> {
        return attendanceRecords.map { it.status }
    }

    private fun isDecliningAttendance(
        statuses: List<AttendanceStatus>
    ): Boolean {
        return statuses.size >= 2 &&
                statuses.first() == AttendanceStatus.PRESENT &&
                statuses.last() == AttendanceStatus.ABSENT
    }
}
enum class AttendanceTrend {
    DECLINING,
    STABLE_OR_IMPROVING
}