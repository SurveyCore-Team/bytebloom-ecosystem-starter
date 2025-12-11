
fun main() {

    val mentees = parseMenteeData()
    val team = parseTeamData()
    val performance = parsePerformanceData()

    println("Mentee count = ${mentees.size}")
    println("Team count = ${team.size}")
    println("Performance rows = ${performance.size}")


        val builder = DomainBuilder()

        val teams = builder.buildDomain(
            performance.toMutableList(),
            team.toMutableList(),
            mentees.toMutableList()
        )
    val menteeList = builder.getMenteeList()
    val firstTeam = teams.first()
    println("____________________________________")
    println("First Team ID: ${firstTeam.teamId}")

    val menteesInsideThisTeam = menteeList.filter { it.teamId == firstTeam.teamId }

    println("Mentees names Within the team :")
    menteesInsideThisTeam.forEach {
        println(it.name)
          }
}






