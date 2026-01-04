package datasource
import model.AttendanceRaw
import model.MenteeRaw
import model.PerformanceSubmissionRaw
import model.ProjectRaw
import model.TeamRaw

interface EcosystemDatasource {
        fun getAllTeams(): List<TeamRaw>
        fun getAllMentees(): List<MenteeRaw>
        fun getAllPerformanceSubmissions(): List<PerformanceSubmissionRaw>
        fun getAllAttendances(): List<AttendanceRaw>
        fun getAllProjects(): List<ProjectRaw>
    }
