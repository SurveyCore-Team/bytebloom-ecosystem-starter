package datasource

import datasource.mapper.DataLinker
import datasource.parser.CsvParser
import model.*

class CsvEcosystemDatasource(private val parser: CsvParser, private val linker: DataLinker) : EcosystemDatasource {
    private val menteesWithDetails: List<MenteeRaw>
    private val teamsWithMembers: List<TeamRaw>

    init {
        menteesWithDetails =
            linker.getFullyLinkedMentees(
                parser.allMentees, parser.allAttendances, parser.allPerformanceSubmissions
            )
        teamsWithMembers = linker.linkTeamsWithMentees(parser.allTeams, menteesWithDetails)
    }

    override fun getAllTeams(): List<TeamRaw> = teamsWithMembers
    override fun getAllMentees(): List<MenteeRaw> = menteesWithDetails
    override fun getAllPerformanceSubmissions(): List<PerformanceSubmissionRaw> = parser.allPerformanceSubmissions
    override fun getAllAttendances(): List<AttendanceRaw> = parser.allAttendances
    override fun getAllProjects(): List<ProjectRaw> = parser.allProjects
}