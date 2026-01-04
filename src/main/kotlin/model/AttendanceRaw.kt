package model

data class AttendanceRaw(
    val menteeId: String,
    val weeklyStatus: Map<String, String>
)
