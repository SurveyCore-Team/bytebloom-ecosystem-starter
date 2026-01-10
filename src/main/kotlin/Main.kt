import datasource.parser.CsvParser
import datasource.CsvEcosystemDatasource
import datasource.mapper.DataLinker
import domain.repository.internal.CsvMenteeRepository
import domain.repository.mapping.DomainMapper
import domain.repository.internal.CsvTeamRepository
import domain.repository.internal.CsvProjectRepository
import domain.services.MenteeService
import domain.services.ReportingService
import domain.services.TeamService

fun main() {
    val csvParser = CsvParser()
    val linker = DataLinker()
    val dataSource = CsvEcosystemDatasource(csvParser, linker)
    val domainMapper = DomainMapper()
    val teamRepository = CsvTeamRepository(dataSource, domainMapper)
    val menteeRepository = CsvMenteeRepository(dataSource, domainMapper)
    val projectRepo = CsvProjectRepository(dataSource, domainMapper)
    val teamService = TeamService(teamRepository, projectRepo)
    val menteeService = MenteeService(menteeRepository)
    val reportService = ReportingService(teamRepository)

    println("==========================================")
    println("   SYSTEM FUNCTIONALITY VERIFICATION      ")
    println("==========================================\n")
    println(">>> [SECTION 1: MENTEE ANALYTICS]")

    val perfectMentees = menteeService.findMenteesWithPerfectAttendance()
    println("Perfect Attendance (100%): ${perfectMentees.map { it.name }.ifEmpty { "No records found" }}")

    val threshold = 2
    val flaggedMentees = menteeService.findMenteesWithPoorAttendance(threshold)
    println("Poor Attendance (Absences > $threshold): ${flaggedMentees.map { it.name }.ifEmpty { "None" }}")

    val topScoreMentee = menteeService.findTopScoringMenteeOverall()
    println("System Top Scorer: ${topScoreMentee?.name}")

    val targetMenteeId = "m001"
    println("\nPerformance Breakdown for ID $targetMenteeId:")
    val breakdown = menteeService.getPerformanceBreakdownForMentee(targetMenteeId)
    if (breakdown.isEmpty()) println("- No submissions found for this mentee.")
    else breakdown.forEach { println("- Task ID: ${it.id} | Score: ${it.score}") }

    // --- 2. TEAM & PROJECT RELATIONSHIPS ---
    println("\n>>> [SECTION 2: TEAM & PROJECT LOGIC]")

    val mentor = teamService.findLeadMentorForMentee(targetMenteeId)
    println("Lead Mentor for $targetMenteeId: ${mentor ?: "Not Assigned"}")

    val targetTeamId = "echo"
    val teamAvg = teamService.getOverallPerformanceAverageForTeam(targetTeamId)
    println("Team $targetTeamId Performance Average: ${String.format("%.2f", teamAvg)}%")

    val project = teamService.findProjectAssignedToTeam(targetTeamId)
    println("Assigned Project for Team $targetTeamId: ${project?.name ?: "No Project Assigned"}")

    val idleTeams = teamService.findTeamsWithNoProject()
    println("Teams without any Projects: ${idleTeams.map { it.name }.ifEmpty { "All teams are assigned" }}")

    // --- 3. REPORTS ---
    println("\n>>> [SECTION 3: ATTENDANCE REPORTS]")
    println("Generating Report for Team $targetTeamId:")
    val attendanceReport = reportService.generateTeamAttendanceReport(targetTeamId)
    if (attendanceReport.isEmpty()) {
        println("- No attendance report available for this team.")
    } else {
        attendanceReport.forEach { (name, records) ->
            println("- Mentee: $name | Records Count: ${records.size}")
        }
    }
    println("\n==========================================")
    println("      VERIFICATION PROCESS COMPLETED      ")
    println("==========================================")
}




