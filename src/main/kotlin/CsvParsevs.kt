import com.bytebloom.model.raw.TeamRaw
import java.io.File

fun parseTeamData(): List<TeamRaw>{
    val result = mutableListOf<TeamRaw>()//Create Empty List
    val lines = File("src/main/resources/teams.csv").readLines()//Read The File

    for (i in 1 until lines.size){
        val parts = lines[i].split(", ")
        val team = TeamRaw(parts[0], parts[1], parts[2])
        result.add(team)
    }
    return result

}