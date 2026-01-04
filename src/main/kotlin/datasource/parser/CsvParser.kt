package datasource.parser

import model.AttendanceRaw
import model.MenteeRaw
import model.PerformanceSubmissionRaw
import model.ProjectRaw
import model.TeamRaw
import java.io.File

class CsvParser {
    val allTeams by lazy { parseTeamsData(readCsvFile("teams.csv")) }
    val allMentees by lazy { parseMenteesData(readCsvFile("mentees.csv")) }
    val allPerformanceSubmissions by lazy { parsePerformanceSubmissionsData(readCsvFile("performance.csv")) }
    val allAttendances by lazy { parseAttendancesData(readCsvFile("attendance.csv")) }
    val allProjects by lazy { parseProjectsData(readCsvFile("projects.csv")) }
    private fun readCsvFile(fileName: String): List<List<String>> {
        val file = File("src/main/resources/$fileName")
        return if (file.exists()) {
            file.readLines().map { it.split(",") }
        } else {
            emptyList()
        }
    }

    private fun parseTeamsData(lines: List<List<String>>) = lines.drop(1).map { row ->
        TeamRaw(row[0], row[1], row[2], emptyList())
    }

    private fun parseMenteesData(lines: List<List<String>>) = lines.drop(1).map { row ->
        MenteeRaw(row[0], row[1], row[2], emptyList(), emptyList())
    }

    private fun parsePerformanceSubmissionsData(lines: List<List<String>>) = lines.drop(1).map { row ->
        PerformanceSubmissionRaw(row[0], row[1], row[2], row[3])
    }

    private fun parseAttendancesData(lines: List<List<String>>): List<AttendanceRaw> {
        val header = lines.firstOrNull() ?: return emptyList()
        return lines.drop(1).map { row ->
            val statusMap = header.indices.drop(1).associate { columnIndex ->
                header[columnIndex] to row[columnIndex]
            }
            AttendanceRaw(row[0], statusMap)
        }
    }

    private fun parseProjectsData(lines: List<List<String>>) = lines.drop(1).map { row ->
        ProjectRaw(row[0], row[1], row[2])
    }
}


