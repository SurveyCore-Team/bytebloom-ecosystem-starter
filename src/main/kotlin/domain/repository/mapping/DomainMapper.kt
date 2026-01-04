package domain.repository.mapping

import domain.Attendance
import domain.Mentee
import domain.PerformanceSubmission
import domain.Projects
import domain.Team
import model.AttendanceRaw
import model.MenteeRaw
import model.PerformanceSubmissionRaw
import model.ProjectRaw
import model.TeamRaw

class DomainMapper {
    private fun mapAttendanceRawToDomain(dataAttendance: AttendanceRaw): List<Attendance> {
        return dataAttendance.weeklyStatus.map { (weekName, statusValue) ->
            Attendance(weekName, statusValue)
        }
    }

    fun mapAttendanceRawToDomainList(dataAttendance: List<AttendanceRaw>) =
        dataAttendance.flatMap { mapAttendanceRawToDomain(it) }

    private fun mapPerformanceSubmissionsRawToDomain(dataRaw: PerformanceSubmissionRaw): PerformanceSubmission {
        return PerformanceSubmission(
            dataRaw.id, dataRaw.type,
            dataRaw.score, dataRaw.menteeId
        )
    }

    fun mapPerformanceSubmissionsRawToDomainList(
        dataPerformanceSubmission: List<PerformanceSubmissionRaw>
    ) = dataPerformanceSubmission.map { mapPerformanceSubmissionsRawToDomain(it) }

    private fun mapMenteeRawToDomain(raw: MenteeRaw): Mentee {
        return Mentee(
            raw.id, raw.name, raw.teamId,
            raw.submissions.map { mapPerformanceSubmissionsRawToDomain(it) },
            raw.attendanceRecords.flatMap { mapAttendanceRawToDomain(it) }
        )
    }

    fun mapMenteeRawToDomainList(dataMentees: List<MenteeRaw>) = dataMentees.map { mapMenteeRawToDomain(it) }

    private fun mapProjectRawToDomain(dataRaw: ProjectRaw): Projects {
        return Projects(dataRaw.id, dataRaw.name, dataRaw.teamId)
    }

    fun mapProjectRawToDomainList(dataProject: List<ProjectRaw>) = dataProject.map { mapProjectRawToDomain(it) }

    private fun mapTeamRawToDomain(raw: TeamRaw): Team {
        return Team(raw.id, raw.name, raw.mentorLead, raw.members.map { mapMenteeRawToDomain(it) })
    }

    fun mapTeamRawToDomainList(dataTeams: List<TeamRaw>) = dataTeams.map { mapTeamRawToDomain(it) }
}