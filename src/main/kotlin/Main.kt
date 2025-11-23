
fun main() {

        val mentees = parseMenteeData()
        val teams = parseTeamData()
        val performance = parsePerformanceData()

        println("Mentee count = ${mentees.size}")
        println("Team count = ${teams.size}")
        println("Performance rows = ${performance.size}")
    }


