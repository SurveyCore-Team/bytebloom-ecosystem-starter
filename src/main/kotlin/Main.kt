import datasource.CsvEcosystemDatasource
import datasource.mapper.DataLinker
import datasource.parser.CsvParser
import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.model.Team
import domain.repository.internal.CsvMenteeRepository
import domain.repository.internal.CsvTeamRepository
import domain.repository.internal.CsvPerformanceSubmissionRepository
import domain.repository.mapping.DomainMapper
import domain.services.MenteeService
import domain.services.TeamService
import repositories.*

fun main() {
}

//    val mentees = parseMenteeData()
//    val team = parseTeamData()
//    val performance = parsePerformanceData()
//
//    println("Mentee count = ${mentees.size}")
//    println("Team count = ${team.size}")
//    println("Performance rows = ${performance.size}")
//    println("____________________________________")
//    val builder = DomainBuilder()
//    val teams = builder.buildDomain(
//        performance.toMutableList(),
//        team.toMutableList(),
//        mentees.toMutableList()
//    )
//    val menteeList = builder.getMenteeList()
//    val firstTeam = teams.first()
//    println("First Team : ${firstTeam.name}")
//
//    val menteesInsideThisTeam = menteeList.filter { it.teamId == firstTeam.id }
//    println("Mentees names Within the team :")
//    menteesInsideThisTeam.forEach {
//        println(it.name)
//    }




