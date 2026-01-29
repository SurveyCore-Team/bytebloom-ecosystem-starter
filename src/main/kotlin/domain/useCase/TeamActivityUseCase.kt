package domain.useCase
import domain.usecase.BaseUseCase
import domain.model.Team
class TeamActivityUseCase :
BaseUseCase<List<Team>, Map<String, TeamActivitySummary>> {
    override fun invoke(
        input: List<Team>
    ): Map<String, TeamActivitySummary> {
        return input.associate { team ->
            val totalSubmissions =
                team.members.sumOf { it.submissions.size }
            val totalAbsences =
                team.members.sumOf {
                    it.attendanceRecords.count { r -> r.status != "Present" }
                }
            team.id to TeamActivitySummary(
                submissions = totalSubmissions,
                absences = totalAbsences
            )
        }
    }
}
data class TeamActivitySummary(
    val submissions: Int,
    val absences: Int
)