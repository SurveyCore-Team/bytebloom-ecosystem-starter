import java.io.File
import com.bytebloom.model.raw.PerformanceRaw
import com.bytebloom.model.raw.TeamRaw

fun parsePerformanceData():List<PerformanceRaw>{
    val file = File ("src/main/resources/performance.csv")
    val lines = file .readLines().drop(1)
    val space = lines.map { line ->
        val space1 = line.split(",")
        PerformanceRaw(menteeId = space1[0],
            submissionId= space1[1],
            submissionType= space1[2],
            score=space1[3])
    }
    return space
}


val csvReadFile = File ("src/main/resources/teams.csv").readLines()
val teamsList = mutableListOf<TeamRaw>()
fun parseTeamData(): List<TeamRaw>{
    for (lineIndex in 1 until csvReadFile.size){ //Start From Second Line.
        val columns = csvReadFile[lineIndex].split(",") //Divid The Line to Colunms.
        val teamRecord = TeamRaw(columns[0], columns[1], columns[2])

        teamsList.add(teamRecord) //Add team to List
    }
    return teamsList
}
