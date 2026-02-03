package domain.usecase
import domain.repository.TeamRepository

class GetOverallPerformanceAverageForTeamUseCase(
    private val teamRepository: TeamRepository
): BaseUseCase<String , Double> {
    override fun invoke(teamId: String): Double =
        teamRepository.getAllTeams()
            .find { it.id == teamId }
            ?.members
            ?.flatMap { it.submissions }
            ?.map { it.score }
            ?.average()
            ?: 0.0
}

