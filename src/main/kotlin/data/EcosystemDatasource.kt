package data
import data.model.AttendanceRaw
import data.model.MenteeRaw
import data.model.PerformanceSubmissionRaw
import data.model.ProjectRaw
import data.model.TeamRaw

interface EcosystemDatasource {
        fun getAllTeams(): List<TeamRaw>
        fun getAllMentees(): List<MenteeRaw>
        fun getAllPerformanceSubmissions(): List<PerformanceSubmissionRaw>
        fun getAllAttendances(): List<AttendanceRaw>
        fun getAllProjects(): List<ProjectRaw>
    }
