package models

import domain.Attendance

data class AttendanceRaw(
    val menteeId: String,
    val week1: String,
    val week2: String,
    val week3: String
)
