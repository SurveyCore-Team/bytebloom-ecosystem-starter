package data.repository

import data.EcosystemDatasource
import domain.model.Mentee
import domain.repository.MenteeRepository
import data.mapper.DomainMapper

class CsvMenteeRepository(
    private val datasource: EcosystemDatasource,
    private val mapper: DomainMapper
) : MenteeRepository {
    override fun getAllMentees(): List<Mentee> {
        val dataAllMentees = datasource.getAllMentees()
        return mapper.mapMenteeRawToDomainList(dataAllMentees)
    }
}