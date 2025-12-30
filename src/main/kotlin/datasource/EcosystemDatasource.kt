package datasource
import models.AttendanceRaw
import models.MenteeRaw
import models.PerformanceSubmissionRaw
import models.ProjectRaw
import models.TeamRaw

interface EcosystemDatasource {
        fun getAllTeams(): List<TeamRaw>
        fun getAllMenteeRaw(): List<MenteeRaw>
        fun getAllPerformanceSubmissionRaw(): List<PerformanceSubmissionRaw>
        fun getAllAttendanceRaw(): List<AttendanceRaw>
        fun getAllProjectRaw(): List<ProjectRaw>
    }
