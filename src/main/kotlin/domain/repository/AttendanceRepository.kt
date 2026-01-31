package domain.repository

import domain.model.attendance.Attendance

interface AttendanceRepository {
    fun getAllAttendances(): List<Attendance>
}