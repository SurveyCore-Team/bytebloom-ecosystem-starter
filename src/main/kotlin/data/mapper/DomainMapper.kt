package data.mapper

import data.model.AttendanceRaw
import data.model.MenteeRaw
import data.model.PerformanceSubmissionRaw
import data.model.ProjectRaw
import data.model.TeamRaw
import domain.model.attendance.Attendance
import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.model.Project
import domain.model.Team
import domain.model.attendance.AttendanceStatus

class DomainMapper {
    private fun mapAttendanceRawToDomain(dataAttendance: AttendanceRaw): List<Attendance> {
        return dataAttendance.weeklyStatus.map { (weekName, statusValue) ->
            Attendance(weekName,AttendanceStatus.fromString(statusValue))
        }
    }

    fun mapAttendanceRawToDomainList(dataAttendance: List<AttendanceRaw>) =
        dataAttendance.flatMap { mapAttendanceRawToDomain(it) }

    private fun mapPerformanceSubmissionsRawToDomain(dataRaw: PerformanceSubmissionRaw): PerformanceSubmission {
        return PerformanceSubmission(
            dataRaw.id, dataRaw.type,
            dataRaw.score.toDoubleOrNull() ?: 0.0,
            dataRaw.menteeId
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

    private fun mapProjectRawToDomain(dataRaw: ProjectRaw): Project {
        return Project(dataRaw.id, dataRaw.name, dataRaw.teamId)
    }

    fun mapProjectRawToDomainList(dataProject: List<ProjectRaw>) = dataProject.map { mapProjectRawToDomain(it) }

    private fun mapTeamRawToDomain(raw: TeamRaw): Team {
        return Team(raw.id, raw.name, raw.mentorLead, raw.members.map { mapMenteeRawToDomain(it) })
    }

    fun mapTeamRawToDomainList(dataTeams: List<TeamRaw>) = dataTeams.map { mapTeamRawToDomain(it) }
}