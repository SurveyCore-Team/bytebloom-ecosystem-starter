import data.parser.CsvParser
import data.CsvEcosystemDatasource
import data.mapper.DataLinker
import data.repository.CsvMenteeRepository
import data.mapper.DomainMapper
import data.repository.CsvTeamRepository
import data.repository.CsvProjectRepository
import domain.usecase.SearchMenteesByNameUseCase
import domain.usecase.AtRiskMenteesUseCase
import domain.usecase.ConsistentHighPerformanceUseCase
import domain.usecase.FindMenteesWithPerfectAttendanceUseCase
import domain.usecase.FindMenteesWithPoorAttendanceUseCase
import domain.usecase.GenerateTeamAttendanceReportUseCase
import domain.usecase.GetMenteesWithAllAbsencesUseCase
import domain.usecase.GetTopMenteesByAttendanceCountUseCase
import domain.usecase.FindLeadMentorForMenteeUseCase
import domain.usecase.GetMentorTeamMenteesUseCase
import domain.usecase.FindTopScoringMenteeOverAllUseCase
import domain.usecase.GetMenteePerformanceSummaryUseCase
import domain.usecase.GetOverallPerformanceAverageForTeamUseCase
import domain.usecase.GetPerformanceBreakdownForMenteeUseCase
import domain.usecase.GetTeamPerformanceRankingUseCase
import domain.usecase.PerformanceConsistencyUseCase
import domain.usecase.FindProjectAssignedToTeamUseCase
import domain.usecase.FindTeamsWithNoProjectUseCase
import domain.usecase.GetProjectTraineesNamesUseCase
import domain.usecase.GetTraineesWithNoProjectsUseCase

fun main() {
    val csvParser = CsvParser()
    val linker = DataLinker()
    val dataSource = CsvEcosystemDatasource(csvParser, linker)
    val domainMapper = DomainMapper()
    val teamRepository = CsvTeamRepository(dataSource, domainMapper)
    val menteeRepository = CsvMenteeRepository(dataSource, domainMapper)
    val projectRepository = CsvProjectRepository(dataSource, domainMapper)
    // 1. Initialize the repository and use case
    println("\n-----------------------Attendance---------------------------\n")
    val findPerfectAttendance = FindMenteesWithPerfectAttendanceUseCase(menteeRepository)
    println("🔍 Checking for mentees with perfect attendance...")
    val perfectAttendees = findPerfectAttendance(Unit)
    when {
        perfectAttendees.isEmpty() -> {
            println("⚠️ No Mentees Found: No one has achieved a 100% attendance record yet.")
        }

        else -> {
            println("✅ Success: Found ${perfectAttendees.size} Perfect Attendance Heroes: ${perfectAttendees.map { it.name }}")
            println("Keep up the great work, everyone! 🌟")
        }
    }
    println("\n--------------------------------------------------\n")
    val findPoorAttendance = FindMenteesWithPoorAttendanceUseCase(menteeRepository)
    println("🔍 Scanning for attendance issues...")
    val poorAttendees = findPoorAttendance(2)
    when {
        poorAttendees.isEmpty() -> {
            println("✅ Great News: No mentees meet the poor attendance criteria.")
        }

        else -> {
            println("⚠️ Attention: Found ${poorAttendees.size} mentees with low attendance: ${poorAttendees.map { it.name }}")
            println("Action Required: Please follow up with these students. 📋")
        }
    }
    println("\n--------------------------------------------------\n")
    val targetTeamId = "bravo"
    println("Generating Report for Team $targetTeamId:")
    val attendanceReport = GenerateTeamAttendanceReportUseCase(teamRepository)(targetTeamId)
    when {
        attendanceReport.isEmpty() -> {
            println("- No attendance report available for this team.")
        }

        else -> {
            attendanceReport.forEach { (name, records) ->
                println("- Mentee: $name | Records Count: ${records.size}")
            }
        }
    }
    println("\n--------------------------------------------------\n")
    val findAllAbsent = GetMenteesWithAllAbsencesUseCase(menteeRepository)
    println("🔍 Checking for critical attendance issues...")
    val absentList = findAllAbsent(Unit)
    when {
        absentList.isEmpty() -> {
            println("✅ Good News: No mentees have a 100% absence record.")
        }

        else -> {
            println("🚨 Critical: Found ${absentList.size} mentees with zero attendance: ${absentList.map { it.name }}")
        }
    }
    println("\n--------------------------------------------------\n")
    val getTopAttendance = GetTopMenteesByAttendanceCountUseCase(menteeRepository)
    println("📊 Generating Attendance Leaderboard...")
    val topMentees = getTopAttendance(5)
    when {
        topMentees.isEmpty() -> {
            println("ℹ️ System Note: No attendance data available to rank mentees.")
        }

        else -> {
            println("🏆 Attendance Leaders (Top ${topMentees.size}):")
            topMentees.forEachIndexed { index, mentee ->
                println("${index + 1}. ${mentee.name} - Sessions Attended: ${mentee.attendanceRecords.size}")
            }
            println("Congratulations to our most committed students! 👏")
        }
    }
    println("\n-----------------------Mentor---------------------------\n")
    val findLeadMentor = FindLeadMentorForMenteeUseCase(teamRepository)
    val targetMenteeId = "m003"
    println("🔍 Identifying the Lead Mentor for Mentee ID: $targetMenteeId...")
    val mentorName = findLeadMentor(targetMenteeId)
    when (mentorName) {
        null -> {
            println("⚠️ Result: No Lead Mentor assigned. This mentee might not be part of any team.")
        }

        else -> {
            println("✅ Success: The Lead Mentor for this mentee is: **$mentorName**")
        }
    }
    println("\n--------------------------------------------------\n")
    val getTeamMentees = GetMentorTeamMenteesUseCase(teamRepository)
    val mentorSearch = "Alice"
    println("📂 Fetching team roster for Mentor: $mentorSearch...")
    val menteeNames = getTeamMentees(mentorSearch)
    when {
        menteeNames == null -> {
            println("❌ Error: Mentor '$mentorSearch' was not found in our records.")
        }

        menteeNames.isEmpty() -> {
            println("⚠️ Note: Mentor '$mentorSearch' exists, but currently has no mentees assigned.")
        }

        else -> {
            println("✅ Success: Found ${menteeNames.size} mentees under $mentorSearch's leadership:")
            menteeNames.forEachIndexed { index, name ->
                println("   ${index + 1}. $name")
            }
        }
    }
    println("\n-----------------------Performance---------------------------\n")
    val findTopScorer = FindTopScoringMenteeOverAllUseCase(menteeRepository)
    println("🏆 Identifying the Overall Top Scoring Mentee...")
    val topMentee = findTopScorer(Unit)
    when (topMentee) {
        null -> println("ℹ️ System Note: No mentee data found to calculate rankings.")
        else -> {
            println("🌟 Grand Achievement: **${topMentee.name}** is our Top Performer!")
            val average = topMentee.submissions.map { it.score }.average()
            println("📊 Average Score: ${"%.2f".format(average)}")
        }
    }
    println("\n--------------------------------------------------\n")
    val getPerformance = GetMenteePerformanceSummaryUseCase(menteeRepository)
    val menteeId = "m009"
    println("📊 Accessing performance metrics for ID: $menteeId...")
    val summary = getPerformance(menteeId)
    when (summary) {
        null -> println("❌ Error: Mentee ID '$menteeId' not found in the system.")
        else -> {
            val (total, average, count) = summary
            println("--- Student Performance Summary ---")
            println("📝 Submissions: $count")
            println("🎯 Total Score: $total")
            println("📈 Avg Grade:   ${"%.2f".format(average)}")
        }
    }
    println("\n--------------------------------------------------\n")
    val getTeamAverage = GetOverallPerformanceAverageForTeamUseCase(teamRepository)
    val teamId = "bravo"
    println("📊 Calculating the overall performance average for Team: $teamId...")
    val averageScore = getTeamAverage(teamId)
    when {
        averageScore > 0 -> println("📈 Team Average Score: ${"%.2f".format(averageScore)}")
        else -> {
            println("⚠️ Note: No performance data available for this team yet.")
        }
    }
    println("\n--------------------------------------------------\n")
    println("\nPerformance Breakdown for ID $targetMenteeId:")
    val breakdown = GetPerformanceBreakdownForMenteeUseCase(menteeRepository)(targetMenteeId)
    when {
        breakdown.isEmpty() -> {
            println("- No submissions found for this mentee.")
        }

        else -> {
            breakdown.forEach { println("- Task ID: ${it.id} | Score: ${it.score}") }
        }
    }
    println("\n--------------------------------------------------\n")
    val getTeamRanking = GetTeamPerformanceRankingUseCase(teamRepository)
    println("🏅 Generating Global Team Performance Rankings...")
    val numberOfTopTeams = 3
    val rankings = getTeamRanking(Unit).take(numberOfTopTeams)
    when {
        rankings.isEmpty() -> {
            println("ℹ️ No team data available to generate rankings.")
        }

        else -> {
            println("--- 🏆 Team Leaderboard ---")
            rankings.forEachIndexed { index, (team, average) ->
                println("${index + 1} ${team.name.padEnd(15)} | Global Average: ${"%.2f".format(average)}")
            }
            println("Analysis complete. Keep up the high standards! 🚀")
        }
    }
    println("\n-----------------------Project---------------------------\n")
    val findIdleTeams = FindTeamsWithNoProjectUseCase(teamRepository, projectRepository)
    println("🔎 Scanning for teams without assigned projects...")
    val idleTeams = findIdleTeams(Unit)
    when {
        idleTeams.isEmpty() -> {
            println("✅ All teams are currently assigned to projects. Excellent resource allocation!")
        }

        else -> {
            println("⚠️ Attention: Found ${idleTeams.size} team(s) without projects:")
            idleTeams.forEach { team ->
                println("   • Team Name: ${team.name.padEnd(15)} | ID: ${team.id}")
            }
        }
    }
    println("\n--------------------------------------------------\n")
    val findProject = FindProjectAssignedToTeamUseCase(projectRepository)
    val teamIdToCheck = "echo"

    println("🔍 Searching for project assigned to Team: $teamIdToCheck...")
    val assignedProject = findProject(teamIdToCheck)
    if (assignedProject != null) {
        println("✅ Project Found:")
        println("   - Name: ${assignedProject.name}")
        println("   - ID: ${assignedProject.id}")
    } else {
        println("ℹ️ Status: This team is not currently assigned to any project.")
    }
    println("\n--------------------------------------------------\n")
    val getTrainees = GetProjectTraineesNamesUseCase(projectRepository, teamRepository)
    val targetProjectId = "p11"
    println("📂 Checking project resources for: $targetProjectId...")
    val trainees = getTrainees(targetProjectId)
    if (trainees.isEmpty()) {
        println("⚠️ Notification: This project currently has no assigned trainees or doesn't exist.")
    } else {
        println("👥 Assigned Team members: ${trainees.joinToString(" | ")}")
    }
    println("\n--------------------------------------------------\n")
    val getIdleTrainees = GetTraineesWithNoProjectsUseCase(teamRepository, projectRepository)
    println("🔍 Checking trainees availability...")
    val idleNames = getIdleTrainees(Unit)
    if (idleNames.isNotEmpty()) {
        println("💡 Potential candidates for new projects: ${idleNames.joinToString(", ")}")
    } else {
        println("✅ All trainees are currently busy.")
    }
    println("\n--------------------------------------------------\n")
    val atRiskUseCase = AtRiskMenteesUseCase(menteeRepository)
    val input = Pair(2, 50.2)
    val atRiskList = atRiskUseCase(input)
    println("=== At-Risk Mentees Report ===")
    when {
        atRiskList.isNullOrEmpty() -> println("No mentees are currently at risk.")
        else -> {
            atRiskList.forEach { mentee ->
                println("Name: ${mentee.name} | Status: AT RISK ⚠️")
            }
        }

    }
    println("\n--------------------------------------------------\n")
    val highPerformanceUseCase = ConsistentHighPerformanceUseCase(menteeRepository)
    val threshold = 99.0
    val starPerformers = highPerformanceUseCase(threshold)
    when {
        starPerformers.isEmpty() -> println("No mentees met the consistency criteria.")
        else -> {
            starPerformers.forEach { mentee ->
                println("Name: ${mentee.name.padEnd(10)} | Status: ⭐ CONSISTENT STAR")
            }
        }
    }
    println("\n--------------------------------------------------\n")
    println("--- Testing Performance Consistency ---")
    val consistencyUseCase = PerformanceConsistencyUseCase(menteeRepository)
    val results = consistencyUseCase(99.5)
    results.forEach { (id, status) ->
        println("Mentee ID: $id -> Stability: $status")
    }
    println("\n--------------------------------------------------\n")
    val menteeName = "clara evans"
    val searchMenteesByName = SearchMenteesByNameUseCase(menteeRepository)
    val findMentee = searchMenteesByName(menteeName)
    when {
        findMentee.isEmpty() -> println("The mentee $menteeName not found ")
        else -> {
            println("The mentee $menteeName is found")
            findMentee.forEach { println("\t-${it.name}") }
        }
    }
}
