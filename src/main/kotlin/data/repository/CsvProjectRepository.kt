package data.repository

import data.EcosystemDatasource
import domain.model.Project
import data.mapper.DomainMapper
import domain.repository.ProjectRepository

class CsvProjectRepository(
    private val datasource: EcosystemDatasource,
    private val mapper: DomainMapper
) : ProjectRepository {
    override fun getAllProjects(): List<Project> {
        val dataAllProjects = datasource.getAllProjects()
        return mapper.mapProjectRawToDomainList(dataAllProjects)
    }
}