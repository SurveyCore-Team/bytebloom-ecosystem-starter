package data.repository

import data.EcosystemDatasource
import domain.model.Team
import domain.repository.TeamRepository
import data.mapper.DomainMapper

class CsvTeamRepository(
    private val datasource: EcosystemDatasource,
    private val mapper: DomainMapper
) :
    TeamRepository {

    override fun getAllTeams(): List<Team> {
        val dataAllTeams = datasource.getAllTeams()
        return mapper.mapTeamRawToDomainList(dataAllTeams)
    }
}