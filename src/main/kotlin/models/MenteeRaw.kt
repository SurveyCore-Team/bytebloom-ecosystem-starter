package models

import domain.Attendance
import domain.PerformanceSubmission

data class MenteeRaw(
    val id:String,
    val name :String,
    val teamId :String,
    var submissions: List<PerformanceSubmissionRaw>,
    val attendanceRecords: List<AttendanceRaw>
)