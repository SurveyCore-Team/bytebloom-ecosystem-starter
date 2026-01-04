package datasource.mapper

import model.AttendanceRaw
import model.MenteeRaw
import model.PerformanceSubmissionRaw
import model.TeamRaw

class DataLinker {
    fun linkAttendanceWithMentees(mentees: List<MenteeRaw>, attendance: List<AttendanceRaw>): List<MenteeRaw> {
        val attendanceMap = attendance.associateBy { it.menteeId }
        mentees.forEach { it.attendanceRecords = listOfNotNull(attendanceMap[it.id]) }
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
        val fullyLinkedMenttes = linkPerformanceSubmissionsWithMentees(menteesWithAttendance, performanceSubmissions)
        return fullyLinkedMenttes
    }
    fun linkTeamsWithMentees(teams: List<TeamRaw>, linkedMentees: List<MenteeRaw>): List<TeamRaw> {
        val membersByTeams = linkedMentees.groupBy { it.teamId }
        teams.forEach { team ->
            team.members = membersByTeams[team.id] ?: emptyList()
        }
        return teams
    }
}