import java.io.File
import com.bytebloom.model.raw.PerformanceRaw
import com.bytebloom.model.raw.TeamRaw
import com.bytebloom.model.raw.MenteeRaw


// Read all CSV files once (outside the functions)
val performanceFileLines = File("src/main/resources/performance.csv").readLines().drop(1)
val teamFileLines = File("src/main/resources/teams.csv").readLines().drop(1)
val menteeFileLines = File("src/main/resources/mentees.csv").readLines().drop(1)

fun parsePerformanceData():List<PerformanceRaw>{

    return performanceFileLines.map { line ->
        val columns = line.split(",")
        PerformanceRaw(menteeId = columns[0],
            submissionId= columns[1],
            submissionType= columns[2],
            score= columns[3])
    }
}
fun parseTeamData(): List<TeamRaw>{
    val teamsList = mutableListOf<TeamRaw>()
    for (lineIndex in 0 until teamFileLines.size){ //Start From Second Line.
        val columns = teamFileLines[lineIndex].split(",") //Divid The Line to Colunms.
        val teamRecord = TeamRaw(columns[0],
                              columns[1],
                               columns[2])

        teamsList.add(teamRecord) //Add team to List
    }
    return teamsList
}
fun parseMenteeData(): List<MenteeRaw> {

    return menteeFileLines.map { line ->
        val columns = line.split(",")
        MenteeRaw(
            Id = columns[0],
            name = columns[1],
            teamId = columns[2]
        )
    }
}

