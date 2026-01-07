package datasource
import datasource.model.AttendanceRaw
import datasource.model.MenteeRaw
import datasource.model.PerformanceSubmissionRaw
import datasource.model.ProjectRaw
import datasource.model.TeamRaw

interface EcosystemDatasource {
        fun getAllTeams(): List<TeamRaw>
        fun getAllMentees(): List<MenteeRaw>
        fun getAllPerformanceSubmissions(): List<PerformanceSubmissionRaw>
        fun getAllAttendances(): List<AttendanceRaw>
        fun getAllProjects(): List<ProjectRaw>
    }
