package repositories

import datasource.EcosystemDatasource
import domain.Attendance
import models.AttendanceRaw

class CsvAttendanceRepository( private val datasource: EcosystemDatasource) : AttendanceRepository {
     fun mappingAttendanceRawToAttendance(dataAttendance: List<AttendanceRaw> ): List<Attendance> {
        return dataAttendance.map { attendanceRaw ->
            listOf(
                Attendance("Week 1", attendanceRaw.week1),
                Attendance("Week 2", attendanceRaw.week2),
                Attendance("Week 3", attendanceRaw.week3)
            )
        }
    }
    override fun getAllAttendances(): List<Attendance> {
        val dataAttendance = datasource.getAllAttendanceRaw()
        return mappingAttendanceRawToAttendance(dataAttendance)
    }

}