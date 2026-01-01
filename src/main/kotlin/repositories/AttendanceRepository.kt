package repositories

import domain.Attendance


interface AttendanceRepository {
    fun getAllAttendances(): List<Attendance>
}