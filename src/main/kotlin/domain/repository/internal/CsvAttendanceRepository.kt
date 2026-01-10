package domain.repository.internal

import datasource.EcosystemDatasource
import domain.model.Attendance
import domain.repository.contracts.AttendanceRepository
import domain.repository.mapping.DomainMapper

class CsvAttendanceRepository(private val datasource: EcosystemDatasource, private val mapper: DomainMapper) :
    AttendanceRepository {

    override fun getAllAttendances(): List<Attendance> {
        val dataAllAttendance =datasource.getAllAttendances()
        return mapper.mapAttendanceRawToDomainList(dataAllAttendance)
    }
}