import data.parser.CsvParser
import data.CsvEcosystemDatasource
import data.mapper.DataLinker
import data.repository.CsvMenteeRepository
import data.mapper.DomainMapper
import data.repository.CsvTeamRepository
import data.repository.CsvProjectRepository
import domain.useCase.mentor.FindLeadMentorForMenteeUseCase
import domain.useCase.mentor.GetMentorTeamMenteesUseCase

fun main() {
    val csvParser = CsvParser()
    val linker = DataLinker()
    val dataSource = CsvEcosystemDatasource(csvParser, linker)
    val domainMapper = DomainMapper()
    val teamRepository = CsvTeamRepository(dataSource, domainMapper)
    val menteeRepository = CsvMenteeRepository(dataSource, domainMapper)
    val projectRepo = CsvProjectRepository(dataSource, domainMapper)
    val mento= FindLeadMentorForMenteeUseCase(teamRepository)
    val getMentorMentees= GetMentorTeamMenteesUseCase(teamRepository)

    println("==========================================")
    println("   SYSTEM FUNCTIONALITY VERIFICATION      ")
    println("==========================================\n")
    println(">>> [SECTION 1: MENTEE ANALYTICS]")
    val resul = mento("m00") ?: "Not Found"
    println("The Mentor of Mentee: $resul")
    val nameMentor= "alice"
    val result = getMentorMentees(nameMentor)
    if (result == null) {
        println("Error: Mentor not found in the system.")
    } else if (result.isEmpty()) {
        println("Warning: Mentor exists, but the team is currently empty.")
    } else {
        println(" Success: Mentees found: $result")
    }


























//    println("The Mentor of Mentee ${mento("m003")}")

//    val perfectMentees = menteeService.findMenteesWithPerfectAttendance()
//    println("Perfect Attendance (100%): ${perfectMentees.map { it.name }.ifEmpty { "No records found" }}")
//
//    val threshold = 2
//    val flaggedMentees = menteeService.findMenteesWithPoorAttendance(threshold)
//    println("Poor Attendance (Absences > $threshold): ${flaggedMentees.map { it.name }.ifEmpty { "None" }}")
//
//    val topScoreMentee = menteeService.findTopScoringMenteeOverall()
//    if(topScoreMentee != null) {
//        val avg = topScoreMentee.submissions.map { it.score.toDouble() }.average()
//        println("The top mentee is ${topScoreMentee.name} with an overall average of ${String.format("%.2f", avg)}")
//    }else{
//        println("Result :No Mentees found or no performance data available")
//    }
//
//    val targetMenteeId = "m001"
//    println("\nPerformance Breakdown for ID $targetMenteeId:")
//    val breakdown = menteeService.getPerformanceBreakdownForMentee(targetMenteeId)
//    if (breakdown.isEmpty()) println("- No submissions found for this mentee.")
//    else breakdown.forEach { println("- Task ID: ${it.id} | Score: ${it.score}") }
//
//    // --- 2. TEAM & PROJECT RELATIONSHIPS ---
//    println("\n>>> [SECTION 2: TEAM & PROJECT LOGIC]")
//
//    val mentor = teamService.findLeadMentorForMentee(targetMenteeId)
//    println("Lead Mentor for $targetMenteeId: ${mentor ?: "Not Assigned"}")
//
//
//    val targetTeamId = "echo"
//    val teamAvg = teamService.getOverallPerformanceAverageForTeam(targetTeamId)
//    println("Team $targetTeamId Performance Average: ${String.format("%.2f", teamAvg)}%")
//
//    val project = teamService.findProjectAssignedToTeam(targetTeamId)
//    println("Assigned Project for Team $targetTeamId: ${project?.name ?: "No Project Assigned"}")
//
//    val idleTeams = teamService.findTeamsWithNoProject()
//    println("Teams without any Projects: ${idleTeams.map { it.name }.ifEmpty { "All teams are assigned" }}")
//
//    // --- 3. REPORTS ---
//    println("\n>>> [SECTION 3: ATTENDANCE REPORTS]")
//    println("Generating Report for Team $targetTeamId:")
//    val attendanceReport = reportService.generateTeamAttendanceReport(targetTeamId)
//    if (attendanceReport.isEmpty()) {
//        println("- No attendance report available for this team.")
//    } else {
//        attendanceReport.forEach { (name, records) ->
//            println("- Mentee: $name | Records Count: ${records.size}")
//        }
//    }
    println("\n==========================================")
    println("      VERIFICATION PROCESS COMPLETED      ")
    println("==========================================")
}




