package data.repository

import data.EcosystemDatasource
import domain.model.Attendance
import domain.repository.AttendanceRepository
import data.mapper.DomainMapper

class CsvAttendanceRepository(private val datasource: EcosystemDatasource, private val mapper: DomainMapper) :
    AttendanceRepository {

    override fun getAllAttendances(): List<Attendance> {
        val dataAllAttendance = datasource.getAllAttendances()
        return mapper.mapAttendanceRawToDomainList(dataAllAttendance)
    }
}