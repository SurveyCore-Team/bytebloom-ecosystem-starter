package domain.repository.internal

import datasource.EcosystemDatasource
import domain.model.Mentee
import domain.repository.contracts.MenteeRepository
import domain.repository.mapping.DomainMapper

class CsvMenteeRepository(
    private val datasource: EcosystemDatasource,
    private val mapper: DomainMapper
) : MenteeRepository {
    override fun getAllMentees(): List<Mentee> {
        val dataAllMentees = datasource.getAllMentees()
        return mapper.mapMenteeRawToDomainList(dataAllMentees)
    }
}