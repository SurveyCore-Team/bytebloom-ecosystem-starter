package domain.repository.internal

import datasource.EcosystemDatasource
import domain.model.Projects
import domain.repository.mapping.DomainMapper
import domain.repository.contracts.ProjectRepository

class CsvProjectRepository(private val datasource: EcosystemDatasource,
                           private val mapper: DomainMapper
) : ProjectRepository {
    override fun getAllProjects(): List<Projects> {
        val dataAllProjects = datasource.getAllProjects()
        return mapper.mapProjectRawToDomainList(dataAllProjects)
    }
}