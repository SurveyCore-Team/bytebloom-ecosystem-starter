package domain

data class Mentee(
    val id: String,
    val name: String,
    val teamId: String,
    var submissions: List<PerformanceSubmission>,
    val attendanceRecords: List<Attendance>
)

