package domain.usecase.attendance

import domain.model.attendance.Attendance
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class GenerateTeamAttendanceReportUseCase(
    private val teamRepository: TeamRepository
) : BaseUseCase<String, Map<String, List<Attendance>>> {
    override fun invoke(teamId: String): Map<String, List<Attendance>> {
        val team = teamRepository.getAllTeams().find { it.id == teamId }
        return team?.members?.associate { it.name to it.attendanceRecords } ?: emptyMap()
    }
}