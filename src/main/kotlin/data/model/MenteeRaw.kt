package data.model

data class MenteeRaw(
    val id:String,
    val name :String,
    val teamId :String,
    var submissions: List<PerformanceSubmissionRaw>,
    var attendanceRecords: List<AttendanceRaw>
)