import datasource.parser.CsvParser
import datasource.mapper.DataLinker
import domain.repository.mapping.DomainMapper
import domain.repository.internal.CsvTeamRepository
import domain.repository.internal.CsvProjectRepository
import domain.services.TeamService
fun main() {
    val csvParser = CsvParser()
    val linker = DataLinker()
    val dataSource = CsvEcosystemDatasource(csvParser, linker)
    val domainMapper = DomainMapper()
    val teamRepo = CsvTeamRepository(dataSource, domainMapper)
    val projectRepo = CsvProjectRepository(dataSource, domainMapper)
    val teamService = TeamService(teamRepo, projectRepo)
    println("--- Teams without projects ---")ِ
    val result = teamService.findTeamsWithNoProject()
    result.forEach { println(it.name) }
}




