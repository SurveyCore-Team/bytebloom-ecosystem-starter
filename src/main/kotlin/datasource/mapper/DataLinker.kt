package datasource.mapper

import datasource.model.AttendanceRaw
import datasource.model.MenteeRaw
import datasource.model.PerformanceSubmissionRaw
import datasource.model.TeamRaw

class DataLinker {
    fun linkAttendanceWithMentees(mentees: List<MenteeRaw>, attendance: List<AttendanceRaw>): List<MenteeRaw> {
        val attendanceMap = attendance.groupBy{ it.menteeId }
        mentees.forEach { it.attendanceRecords = (attendanceMap[it.id] ?: emptyList()) }
        return mentees
    }

    fun linkPerformanceSubmissionsWithMentees(
        mentees: List<MenteeRaw>,
        performanceSubmissions: List<PerformanceSubmissionRaw>
    ): List<MenteeRaw> {
        val performanceSubmissionsMap = performanceSubmissions.groupBy { it.menteeId }
        mentees.forEach { it.submissions = (performanceSubmissionsMap[it.id] ?: emptyList()) }
        return mentees
    }

    fun getFullyLinkedMentees(
        mentees: List<MenteeRaw>,
        attendance: List<AttendanceRaw>,
        performanceSubmissions: List<PerformanceSubmissionRaw>
    ): List<MenteeRaw> {
        val menteesWithAttendance = linkAttendanceWithMentees(mentees, attendance)
        val fullyLinkedMentees = linkPerformanceSubmissionsWithMentees(menteesWithAttendance, performanceSubmissions)

        return fullyLinkedMentees
    }
    fun linkTeamsWithMentees(teams: List<TeamRaw>, linkedMentees: List<MenteeRaw>): List<TeamRaw> {
        val membersByTeams = linkedMentees.groupBy { it.teamId }
        teams.forEach { team ->
            team.members = membersByTeams[team.id] ?: emptyList()
        }
        return teams
    }
}