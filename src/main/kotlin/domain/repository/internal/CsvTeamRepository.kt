package domain.repository.internal

import datasource.EcosystemDatasource
import domain.model.Team
import domain.repository.contracts.TeamRepository
import domain.repository.mapping.DomainMapper

class CsvTeamRepository(private val datasource: EcosystemDatasource, private val mapper: DomainMapper) :
    TeamRepository {

    override fun getAllTeams(): List<Team> {
        val dataAllTeams =datasource.getAllTeams()
        return mapper.mapTeamRawToDomainList(dataAllTeams)
    }
}