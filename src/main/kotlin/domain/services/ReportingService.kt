package domain.services

import domain.model.Attendance
import domain.repository.contracts.TeamRepository
import java.util.Collections.emptyMap


class ReportingService(
    private val teamRepository: TeamRepository
) {

    fun generateTeamAttendanceReport(teamId: String): Map<String, List<Attendance>> {
        val team = teamRepository.getAllTeams().find { it.id == teamId }
        return team?.members?.associate { it.name to it.attendanceRecords } ?: emptyMap()
    }
}
