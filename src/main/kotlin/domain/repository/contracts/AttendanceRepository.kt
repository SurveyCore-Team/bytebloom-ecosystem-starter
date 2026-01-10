package domain.repository.contracts

import domain.model.Attendance

interface AttendanceRepository {
    fun getAllAttendances(): List<Attendance>

}